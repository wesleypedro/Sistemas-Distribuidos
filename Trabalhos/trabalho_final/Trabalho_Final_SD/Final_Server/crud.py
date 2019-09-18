import MySQLdb
import json

con = None
cursor = None


def open():
    host = "localhost"
    user = "root"
    password = "admin"
    db = "tfDistribuidos"
    port = 3306

    global con
    global cursor

    con = MySQLdb.connect(host, user, password, db, port)
    cursor = con.cursor(MySQLdb.cursors.DictCursor)
    return (con, cursor)

def close():
    global con
    global cursor

    cursor.close()
    con.close


def select(fields, tables, where=None):

    global cursor
    open()
    
    query = "SELECT " + fields + " FROM " + tables
    if(where):
        query = query + " WHERE " + where
    
    cursor.execute(query)
    ret = cursor.fetchall()
    close()

    return ret


def insert(values, table, fields=None):

    global cursor, con
    open()

    query = "INSERT INTO " + table
    if(fields):
        query = query + " ( " + fields + ") "
    query = query + " VALUES " + ",".join(["(" + v + ")" for v in values])
    
    cursor.execute(query)
    con.commit()

    close()



def update(sets, table, where=None):

    global cursor, con
    open()

    query = "UPDATE " + table
    query = query + " SET " + ", ".join([str(field) + " = '" + str(value) + "'" for field, value in sets.items()])
    if(where):
        query = query + " WHERE " + where
    cursor.execute(query)
    con.commit()
    close()



def delete(table, where):
    
    global cursor, con
    open()
    query = "DELETE FROM " + table + " WHERE " + where
    cursor.execute(query)
    con.commit()
    close()



def updateSenha(newPass, where):

    global cursor, con
    open()
    query = "UPDATE login"
    query = query + " SET " + newPass
    query = query + " WHERE " + where

    cursor.execute(query)
    con.commit()
    close()