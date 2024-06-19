<?php
include "bdlamichoacana.php";
$bd = new BaseDeDatos();
$proceso = 0;
//Ingresar
if (isset($_GET['usr']) && isset($_GET['pass'])){
    $usr = $_GET['usr'];
    $pass = $_GET['pass'];
    
    $res = $bd->ingreso($usr,$pass);
    echo '{"usr":'.$res.'}';  
}
//Modificar
if(
    isset($_GET['nombre']) &&
    isset($_GET['sabor']) &&
    isset($_GET['tamaño']) &&
    isset($_GET['cantidad']) &&
    isset($_GET['domicilio']) &&
    isset($_GET['telefono']) &&
    isset($_GET['hora']) && 
    isset($_GET['fecha']) && 
    isset($_GET['id'])
    ){
    $nombre = $_GET['nombre'];
    $sabor = $_GET['sabor'];
    $tamaño = $_GET['tamaño'];
    $cantidad = $_GET['cantidad'];
    $domicilio = $_GET['domicilio'];
    $telefono = $_GET['telefono'];
    $hora = $_GET['hora'];
    $fecha = $_GET['fecha'];
    $id = $_GET['id'];

    $bd->modificar($id,$nombre, $sabor, $tamaño, $cantidad, $domicilio, $telefono, $hora, $fecha);
    
    echo '{"id":'.$id.',"nombre":"'.$nombre.'","sabor":"'.$sabor.'","tamaño":"'.$tamaño.'","cantidad":"'.$cantidad.'","domicilio":"'.$domicilio.'","telefono":"'.$telefono.'","hora":"'.$hora.'","fecha":"'.$fecha.'"}';
    }else{
//Agregar
if (
    isset($_GET['nombre']) &&
    isset($_GET['sabor']) &&
    isset($_GET['tamaño']) &&
    isset($_GET['cantidad']) &&
    isset($_GET['domicilio']) &&
    isset($_GET['telefono']) &&
    isset($_GET['hora']) &&
    isset($_GET['fecha'])
){
    $nombre = $_GET['nombre'];
    $sabor = $_GET['sabor'];
    $tamaño = $_GET['tamaño'];
    $cantidad = $_GET['cantidad'];
    $domicilio = $_GET['domicilio'];
    $telefono = $_GET['telefono'];
    $hora = $_GET['hora'];
    $fecha = $_GET['fecha'];
  
    $res = $bd->agrega($nombre, $sabor, $tamaño, $cantidad, $domicilio, $telefono, $hora, $fecha);
    echo '{"id":'.$res.',"nombre":"'.$nombre.'","sabor":"'.$sabor.'","tamaño":"'.$tamaño.'","cantidad":"'.$cantidad.'","domicilio":"'.$domicilio.'","telefono":"'.$telefono.'","hora":"'.$hora.'","fecha":"'.$fecha.'"}';
}
}
//Eliminar
if(isset($_GET['id']) && isset($_GET['elimina'])){
    $id = $_GET['id'];
    $bd->elimina($id);
    print_r("Exito");
}
?>