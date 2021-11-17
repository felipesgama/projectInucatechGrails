package helloWord.lojista

import grails.gorm.transactions.Transactional;
import grails.gorm.transactions.Transactional
import java.sql.Time;
import java.text.SimpleDateFormat; 
import groovy.time.TimeCategory; 
import java.util.Date;  
import groovy.sql.Sql;
import javax.annotation.PostConstruct;
import helloWord.Usuario;
import helloWord.Loja;
import helloWord.Produto;
@Transactional
class ProdutoService {

    def dataSource;
    def query=null;

    @PostConstruct
    def init(){
        query = new Sql(dataSource);
    }


    def serviceMethod() {

    }
 

    def save(def user_id,def recebido){
        def usuario = Usuario.get(user_id);
        def loja =usuario.lojas[0].loja;
        def produto = new Produto();
        produto.loja = loja;
        produto.valor = recebido.valor;
        produto.codigo  =recebido.codigo;
        produto.codigoBarras = recebido.codigoBarras;
        produto.nome = recebido.nome;
        produto.save(flush:true);
        return [codereturn: 0,message: "Produto cadastrado com sucesso!"];

    }




}
