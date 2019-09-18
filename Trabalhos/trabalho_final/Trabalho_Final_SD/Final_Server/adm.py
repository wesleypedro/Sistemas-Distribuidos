import crud

# MethodId = 1
def cadastrarIngresso(arguments):
    nome = arguments['nome']
    qtd = int(arguments['quantidade'])
    horIni = arguments['horarioInicio']
    horTer = arguments['horarioTermino']
    data = arguments['data']
    end = arguments['endereco']
    preco = float(arguments['preco'])

    values = ["DEFAULT, '"+str(nome)+"', "+str(qtd)+", '"+str(horIni)+"', '"+str(horTer)+"', '"+str(data)+"', '"+str(end)+"', "+str(preco)]
    crud.insert(values, "ingresso")

    where = "nome = '"+str(nome)+"' and quantidade = "+str(qtd)+" and horarioInicio = '"+str(horIni)+"' and horarioTermino = '"+str(horTer)+"' and data = '"+str(data)+"' and endereco = '"+str(end)+"' and preco = "+str(preco)

    codigo = crud.select("codigo", "ingresso", where)

    reply = {
        'codigo': int(codigo[0]['codigo']),
        'nome': nome,
        'quantidade': int(qtd),
        'horarioInicio': horIni,
        'horarioTermino': horTer,
        'data': data,
        'endereco': end,
        'preco': float(preco)
    }

    return reply


# MethodId = 2
def venderIngresso(arguments):
    codigo = arguments['codigo']
    quantidade = arguments['quantidade']

    crud.update({"quantidade":quantidade}, "ingresso", "codigo = "+str(codigo))

    ingresso = crud.select("*", "ingresso", "codigo = "+str(codigo))
    return ingresso[0]


# MethodId = 3
def listarIngressos(arguments):

    reply = [{
        'codigo': int(-1),
        'nome': "Nao ha ingressos para retornar",
        'quantidade': int(-1),
        'horarioInicio': "---",
        'horarioTermino': "---",
        'data': "---",
        'endereco': "---",
        'preco': float(-1)
    }]

    if(arguments['request'] == 'all'):
        ingresso = crud.select("*", "ingresso")
        if len(ingresso) == 0:
            return reply

        return ingresso
    return reply


# MethodId = 4
def pesquisarIngresso(arguments):
    where = arguments['query']

    ingresso = crud.select("*", "ingresso", where)

    if len(ingresso) == 0:
        reply = [{
            'codigo': int(-1),
            'nome': "Nao ha ingressos cadastrados",
            'quantidade': int(-1),
            'horarioInicio': "--",
            'horarioTermino': "--",
            'data': "--",
            'endereco': "--",
            'preco': float(-1)
        }]

        return reply
    return ingresso


# MethodId = 5
def atualizarIngresso(arguments):
    
    codigo = arguments['codigo']

    nome = arguments['nome']
    qtd = int(arguments['quantidade'])
    horIni = arguments['horarioInicio']
    horTer = arguments['horarioTermino']
    data = arguments['data']
    end = arguments['endereco']
    preco = float(arguments['preco'])

    crud.update({"nome":nome, "quantidade":qtd, "horarioInicio":horIni, "horarioTermino":horTer, "data":data, "endereco": end, "preco":preco}, "ingresso", "codigo = "+str(codigo))

    ingresso = crud.select("*", "ingresso", "codigo = "+str(codigo))
    return ingresso[0]


# MethodId = 6
def removerIngresso(arguments):
    codigo = arguments['codigo']

    where = "codigo = "+str(codigo)

    crud.delete("ingresso", where)

    ingresso = crud.select("*", "ingresso", where)

    if len(ingresso) == 0:
        reply = {
            'codigo': int(-1),
            'nome': "",
            'quantidade': int(-1),
            'horarioInicio': "",
            'horarioTermino': "",
            'data': "",
            'endereco': "",
            'preco': float(-1)
        }

        return reply
    return ingresso[0]


# MethodId = 7
def cadastrarUsuario(arguments):
    cpf = arguments['cpf']
    username = arguments['username']
    password = arguments['password']
    category = arguments['category']

    retorno = crud.select("*", "login", "cpf = '"+cpf+ "'")
    if len(retorno) > 0:
        existente = {
            'cpf':"0",
            'username':"",
            'password':"",
            'category':""
        }
        return existente
    
    values = ["'"+str(cpf)+"', '"+str(username)+"', '"+str(password)+"', '"+str(category)+"'"]
    crud.insert(values, "login")
    
    retorno2 = crud.select("*", "login", "cpf = '"+cpf+ "'")
    if len(retorno2) > 0:
        novo = {
            'cpf':str(retorno2[0]['cpf']),
            'username':str(retorno2[0]['username']),
            'password':str(retorno2[0]['password']),
            'category':str(retorno2[0]['password'])
        }
        return novo
        

# MethodId = 8
def removerUsuario(arguments):
    cpf = arguments['cpf']
    
    where = "cpf = '"+str(cpf)+"'"
    crud.delete("login", where)

    retorno = crud.select("*", "login", "cpf = '"+cpf+ "'")

    if len(retorno) == 0:
        naoExiste = {
            'cpf':"removido",
            'username':"",
            'password':"",
            'category':""
        }
        return naoExiste
    
    
    return retorno[0]


# MethodId = 9
def alterarSenha(arguments):
    cpf = arguments['cpf']
    password = arguments['password']
    newPassword = arguments['newPassword']

    where = "cpf = '"+cpf+"' and password = '"+password+"'"

    crud.updateSenha("password = '"+newPassword+"'", where)

    user = crud.select("cpf, password", "login", "cpf = "+str(cpf))

    if user[0]['password'] == password:
        naoAlterado = {
            'cpf':"-1",
            'username':"Erro",
            'category':"Erro"
        }
        return naoAlterado
    
    alterado = {
        'cpf':"0",
        'username':"success",
        'category':"success"
    }
    return alterado


# Aux MethodId _8
# MethodId 0
def pesquisarUsuario(arguments):
    cpf = arguments['cpf']
    # password = arguments['password']

    retorno = crud.select("*", "login", "cpf = '"+str(cpf)+ "'")
    if len(retorno) == 0:
        naoExiste = {
            'cpf':"-1",
            'username':"",
            'password':"",
            'category':""
        }
        return naoExiste
    return retorno[0]


# Aux MethodId _9
# MethodId 11
def pesquisarUsuarioSenha(arguments):
    cpf = arguments['cpf']
    password = arguments['password']

    retorno = crud.select("*", "login", "cpf = '"+str(cpf)+ "' and password = '"+str(password)+"'")
    if len(retorno) == 0:
        naoExiste = {
            'cpf':"-1",
            'username':"",
            'password':"",
            'category':""
        }
        return naoExiste
    return retorno[0]