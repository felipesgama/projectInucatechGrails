package helloword

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/v1/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }


        post "/api/v1/cliente/$controller"(action:"save")
        get "/api/v11cliente/$controller/$id(.$format)?"(action:"show")
        get "/api/v1/cliente/$controller/(.$format)?"(action:"index")
        put "/api/v1/cliente/$controller/$id"(action:"update")
        delete "/api/v1/cliente/$controller/$id(.$format)?"(action:"delete")

 


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
