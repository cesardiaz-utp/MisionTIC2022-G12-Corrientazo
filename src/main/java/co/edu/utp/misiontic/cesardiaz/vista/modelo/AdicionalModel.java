/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic.cesardiaz.vista.modelo;

import co.edu.utp.misiontic.cesardiaz.modelo.Adicional;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ROG
 */
public class AdicionalModel extends AbstractTableModel {

    private List<Adicional> datos;

    public AdicionalModel(List<Adicional> datos) {
        this.datos = datos;
        fireTableDataChanged();
    }
    
    public void addRow(Adicional adicional){
        this.datos.add(adicional);
        var row = getRowCount() - 1;
        fireTableRowsInserted(row, row);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Precio";
        }
        return super.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object value = null;
        var adicional = datos.get(row);
        switch (column) {
            case 0:
                value = adicional.getNombre();
                break;
            case 1:
                value = adicional.getPrecio();
                break;
        }
        return value;
    }

}
