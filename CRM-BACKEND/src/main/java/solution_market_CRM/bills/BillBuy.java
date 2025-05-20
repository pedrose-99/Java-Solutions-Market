package solution_market_CRM.bills;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import solution_market_CRM.model.Client;
import solution_market_CRM.model.Inventory;
import solution_market_CRM.model.Product;
import solution_market_CRM.model.Buys;

public class BillBuy
{
    public BillBuy(){}
    public double getPorcentajeIva(String description)
    {
        double iva;
    
        if (description == null)
        {
            iva = 0.21;
        }
        else if (description.toLowerCase().contains("comida"))
        {
            iva = 0.1;
        }
        else if (description.toLowerCase().contains("medicamento"))
        {
            iva = 0.04;
        }
        else
        {
            iva = 0.21;
        }
        return iva;
    }
    public double getBaseImponible(double total, String description)
    {
        double iva = getPorcentajeIva(description);
        return (total / (iva + 1));
    }

    public double getIva (double total, String description)
    {
        double baseImponible = getBaseImponible(total, description);
        return (total - baseImponible);
    }
    public void generateBill(Buys buy, Product product)
    {
        String nombre = "factura_venta_"+buy.getBuy_id()+".txt";
        String ruta = "facturas/facturas_compras/" + nombre;
        LocalDate date = LocalDate.now();
        double importe = buy.getTotal_price();

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(ruta)))
        {
            wr.write("----------------------------------------------------------\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Factura de compra "+buy.getBuy_id()+"\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Detalles de factura"+"\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Fecha: " + date + "\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Tema: " + product.getName()+"\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Importe Total + " + importe+"\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Cliente\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Factura a Nexora Tech\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Dirección: Antartida\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Teléfono: 698712098\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Correo electrónico: nexoraTech@gmail.com\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Resumen del pago\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("Tipo de artículo: " +product.getName()+"\n");
            wr.write("Descripción: "+ product.getDescription() +"\n");
            wr.write("Unidades: "+ buy.getStock()+"\n");
            wr.write("Precio unidad: "+ product.getPrice()+"\n");
            wr.write("Porcentaje iva: " + getPorcentajeIva(product.getDescription()) + "\n");
            wr.write("Base imponible: "+getBaseImponible(importe, product.getDescription()) +"\n");
            wr.write("Iva: "+ getIva(importe, product.getDescription()) +"\n");
            wr.write("Total: "+ importe +"\n");
            wr.write("----------------------------------------------------------\n");
            wr.write("----------------------------------------------------------\n");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

