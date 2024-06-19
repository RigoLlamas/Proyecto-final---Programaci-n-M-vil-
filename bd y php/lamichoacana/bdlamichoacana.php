<?php

    class BaseDeDatos
    {
        private $con;
        public function __construct()
        {
            $this->con = new PDO('mysql:host=localhost;dbname=michoacana','root','');
        }
        public function ingreso($usr,$pass)
        {
            $sql = $this->con->prepare("SELECT * FROM usuarios WHERE BINARY usuarios.nombre_usuario = '" . $usr. "' and BINARY contrasena = '" . $pass ."'" );
            $sql->execute();
            $res = $sql->fetchAll();
 
            if (count($res) > 0)
            {
                foreach ($res as $dato)
                    return $dato['id'];
            }
            return -1;
        }
        //Agregar
        public function agrega($nombre, $sabor, $tamaño, $cantidad, $domicilio, $telefono, $hora, $fecha)
        {
            $sql = $this->con->prepare("INSERT INTO pedido (nombre, sabor, tamano, cantidad, telefono, domicilio, hora, fecha) VALUES ('$nombre', '$sabor', '$tamaño', '$cantidad', '$telefono', '$domicilio', '$hora', '$fecha')");
            $sql->execute();

            $sql = $this->con->prepare("SELECT MAX(id) as id FROM pedido;");
            $sql->execute();
            $id = $sql->fetchColumn();

            return $id;
        }
        //Elimina
        public function elimina($id){
            $sql = $this->con->prepare("DELETE FROM pedido WHERE id = $id");
            $sql->execute();
        }
        //Modifica
        public function modificar($id,$nombre, $sabor, $tamaño, $cantidad, $domicilio, $telefono, $hora, $fecha){
            $sql = $this->con->prepare("UPDATE pedido SET nombre = '$nombre', sabor = '$sabor', tamano = '$tamaño', cantidad = '$cantidad', domicilio = '$domicilio', telefono = '$telefono', hora = '$hora', fecha = '$fecha' WHERE id = $id");
            $sql->execute();
        }
        //Cargar
        public function cargar()
        {
            $sql = $this->con->prepare("SELECT * FROM pedido");
            $sql->execute();
            return $res = $sql->fetchall();
        }
       }
?>