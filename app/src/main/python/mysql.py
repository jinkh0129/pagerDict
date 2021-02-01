import pymysql
def getCode(company_name):
    conn = pymysql.connect(host='localhost',user='root',password='rlagudwls0129',db='investar',charset='utf8')
    cur = conn.cursor()
    sql = "SELECT code FROM company_info WHERE company=%s"
    cur.execute(sql, str(company_name)) # 쿼리문을 실행
    row = cur.fetchone()
    return row[0]

