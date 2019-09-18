import crud

# MethodId = 1
def venderIngresso(arguments):
    codigo = arguments['codigo']
    quantidade = arguments['quantidade']

    crud.update({"quantidade":quantidade}, "ingresso", "codigo = "+str(codigo))

    ingresso = crud.select("*", "ingresso", "codigo = "+str(codigo))
    return ingresso[0]

# MethodId = 2
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

# MethodId = 3
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

# MethodId = 4
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

# Aux MethodId _4
# MethodId 5
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