<%-- 
    Document   : index
    Created on : 8/04/2020, 08:48:00 PM
    Author     : Manuela-Garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="vistas/javascript/validar.js"></script>
        <title>Empleados</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Empleado</h1>
                <form action="Controlador" name="formulario" onsubmit="return revisar()">
                    Identificacion:<br>
                    <input class="form-control" type="text" name="txtDni"  onkeypress="return soloNumeros(event)" required="required"><br>
                    Nombres: <br>
                    <input class="form-control" type="text" name="txtNom" onkeypress="return soloLetras(event)" required="required"><br>
                    Apellidos: <br>
                    <input class="form-control" type="text" name="txtApe" onkeypress="return soloLetras(event)" required="required"><br>
                    Cargo: <br>
                    <input class="form-control" type="text" name="txtCar" onkeypress="return soloLetras(event)" required="required" ><br>
                    Sexo: <br>
                    <select name="selSex" class="form-control">
                        <option value="H">Hombre</option>
                        <option value="M">Mujer</option>
                    </select><br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    <a href="Controlador?accion=listar">Regresar</a>
                </form>
            </div>
        </div>          
        <script type="text/javascript" src="javascript/validar.js"></script> 
        <script>
          
        
        function soloLetras(e){    
            key =e.keyCode || e.which;
           
            teclado = String.fromCharCode(key).toLowerCase();
            letras =" abcdefghijklmnñopqrstuvwxyz";
            especiales = "8-37-38-46-164";
            teclado_especial= false;
           
            for(var i in especiales){
                
                if(key==especiales[i]){
                    teclado_especial = true;
                   break;
                }
            }
            if(letras.indexOf(teclado)== -1 && !teclado_especial){
                return false;
            }
        }
      
        function soloNumeros(e){
            key =e.keyCode || e.which;
            teclado = String.fromCharCode(key);

            numeros ="0123456789";
            especiales = "8-37-38-46";

            teclado_especial= false;
            for(var i in especiales){
                if(key==especiales[i]){
                    teclado_especial = true;
                }
            }
            if(numeros.indexOf(teclado)== -1 && !teclado_especial){
                return false;
            }
    
        }

        
        </script>

    </body>
</html>
