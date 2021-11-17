package helloWord.lojista
import groovy.json.*
import grails.converters.JSON
import java.util.concurrent.TimeUnit;
import java.io.InputStream;
import grails.transaction.Transactional;
import helloWord.lojista.ProdutoService;
/*
*   @author: Felipe Gama
*/
class ProdutoController {

    def produtoService;
    def springSecurityService;

    def save(){
        def recebido = request.JSON;
        if(recebido.valor==null   ){ 
			response.status = 400;
			render([codereturn: 999,
                message: 'Requisição faltando dados'] as JSON)
			return
		}else{
            def id =  springSecurityService.principal.id;
            def result = produtoService.save(id,recebido);
            response.status = 200;
            if(result.codereturn>0){
                response.status = 400;
            }
            render result as JSON;
        }

    }

}
