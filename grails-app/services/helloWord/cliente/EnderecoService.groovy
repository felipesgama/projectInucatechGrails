package helloWord.cliente

import grails.gorm.transactions.Transactional;
import grails.gorm.transactions.Transactional
import java.sql.Time;
import java.text.SimpleDateFormat; 
import groovy.time.TimeCategory; 
import java.util.Date;  
import groovy.sql.Sql;
import javax.annotation.PostConstruct;
import helloWord.Usuario;
import helloWord.Endereco;
import helloWord.EnderecoUsuario;
/*
* @author: Felipe Gama
*/
@Transactional
class EnderecoService {

    def dataSource;
    def query=null;

    @PostConstruct
    def init(){
        query = new Sql(dataSource);
    }

    def listar(def user_id){
        def sql = " SELECT en.logradouro, en.cep "+
            " FROM endereco as en  "+
                " INNER JOIN rel_endereco_usuario as rel "+
                    " ON rel.endereco_id = en.id "+
            " WHERE rel.usuario_id = ${user_id} ";
        def result = query.rows(sql);
        return [enderecos: result]
    }


    def cadastrar(def user_id,def recebido){
        def usuario = Usuario.get(user_id);
        if(!usuario){
            return [codereturn: 100,message: "Esse usuário não existe"]
        }
        def endereco = new Endereco();
        endereco.cep = recebido.cep;
        endereco.bairro = recebido.bairro;
        endereco.cidade = recebido.cidade;
        endereco.estado = recebido.estado;
        if(recebido.complemento?.trim()){
            endereco.complemento = recebido.complemento;
        }
        endereco.logradouro = recebido.logradouro;
        endereco.numero = recebido.numero;
        if(endereco.save()){
            def rel = new  EnderecoUsuario();
            rel.endereco =endereco;
            rel.usuario=usuario;
            rel.save();
        }else{
            return [codereturn: 200, message: "Não foi possível salvar!"];
        }
        
        return [codereturn: 0, message: "Endereço cadastrado com sucesso!"];

    }

}
