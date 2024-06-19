<?php
include "bdlamichoacana.php";
$bd = new BaseDeDatos();
$proceso = 0;

//Cargar
$res = $bd->cargar();
$res = json_encode($res);
echo $res;
?>