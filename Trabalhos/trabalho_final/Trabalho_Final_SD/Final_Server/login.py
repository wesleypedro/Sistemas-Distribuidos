import crud

def logar(cpf, password):
    ret = crud.select("username, password, category", "login", "cpf = '" + str(cpf) + "' and password = '" + str(password)+"'")
    if(len(ret) == 1):
        stat = True
        username = ret[0]['username']
        category = ret[0]['category']
    else:
        username = ""
        stat = False
        category = ""

    return (username, stat, category)