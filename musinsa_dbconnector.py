import pymysql
import os
from musinsa_crawler import crawling
import sys

start_page =int(sys.argv[1])
end_page =int(sys.argv[2])

AWS_RDS_USER=os.environ.get("AWS_RDS_USER")
AWS_RDS_PORT=os.environ.get("AWS_RDS_PORT")
AWS_RDS_PASSWD=os.environ.get("AWS_RDS_PASSWD")
AWS_RDS_HOST=os.environ.get("AWS_RDS_HOST")
AWS_RDS_DB=os.environ.get("AWS_RDS_DB")

data = crawling(start_page, end_page)

conn = pymysql.connect(
    user=AWS_RDS_USER,
    passwd=AWS_RDS_PASSWD,
    host=AWS_RDS_HOST,
    port=int(AWS_RDS_PORT),
    db=AWS_RDS_DB,
    charset='utf8'
)

cursor = conn.cursor(pymysql.cursors.DictCursor)

price_sql = "INSERT INTO price(product_id, rank, price, del_price, rating, rating_count, created_date, coupon, real_price) values(%s,%s, %s, %s, %s, %s, %s, %s, %s)"
product_sql = """INSERT INTO product(product_id, img, product_name, product_url, brand, brand_url, modified_date, category, rank, real_price) 
                values(%s,%s, %s, %s, %s, %s, %s, %s, %s, %s) 
                ON DUPLICATE KEY UPDATE 
                product_id = values(product_id), img = values(img), product_name = values(product_name), product_url = values(product_url), brand = values(brand), 
                brand_url = values(brand_url), modified_date = values(modified_date) , category = values(category), rank = values(rank), real_price = values(real_price)"""

price_list=[]
product_list=[]

for d in data:
    price = (str(d["item_id"]), str(d["rank"]), str(d["price"]), str(d["del_price"]), d["rating"], str(d["rating_count"]), str(d["time"]), str(d["coupon"]), str(int(d["price"]) + int(d["coupon"])))
    product = (str(d["item_id"]), str(d["img"]), str(d["product_name"]), str(d["product_url"]), d["brand"], str(d["brand_url"]), str(d["time"]), d["category"],str(d["rank"]), str(int(d["price"]) + int(d["coupon"])))
    price_list.append(price)
    product_list.append(product)

cursor.executemany(price_sql, price_list)
cursor.executemany(product_sql, product_list)


conn.commit()