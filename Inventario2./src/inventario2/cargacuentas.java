/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

/**
 *
 * @author chr97lubuntu
 */

  

import java.sql.Connection;





public class cargacuentas {
      Conexion con = new Conexion();
    Connection Consulta = con.conexion();
    
    String balance = "INSERT INTO cuentasbalance(nombre, saldo)"+"values("+"Caja"+",0),("+"Caja chica"+",0),("+"Bancos"+",0),("+"IVA por cobrar"+",0),("+"Mercaderías"+",0),("+"Material de empaque"+",0),("+"Papelería y utiles"+",0),("+"Repuestos y accesorios"+",0),("+"Utiles y enseres"+",0),("+"Inmuebles"+",0),("+"Mobiliario y equipo"+",0),("+"Equipo de computación"+",0),("+"Herramientas"+",0),("+"Gastos de organización"+",0),("+"Gastos de instalación"+",0),("+"Préstamos bancarios a corto plazo"+",0),("+"IVA por pagar"+",0),("+"Hipotecas a corto plazo"+",0),("+"Hipotecas a largo plazo"+",0),("+"Préstamos bancarios a largo plazo"+",0),("+"Capital"+",0),("+"Ganancia neta del ejercicio"+",0)";
    
    
    
    
    
}
