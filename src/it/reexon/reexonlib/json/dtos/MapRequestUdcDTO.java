package it.reexon.reexonlib.json.dtos;

public class MapRequestUdcDTO
{
    private String codiceUdc;

    private String colore;

    private String trasparenza;

    private String spessoreLinea; //TODO: set spessoreLinge from String to Long?

    
    
    public MapRequestUdcDTO(String codiceUdc, String colore, String trasparenza, String spessoreLinea)
    {
        super();
        this.codiceUdc = codiceUdc;
        this.colore = colore;
        this.trasparenza = trasparenza;
        this.spessoreLinea = spessoreLinea;
    }

    public String getCodiceUdc()
    {
        return codiceUdc;
    }

    public void setCodiceUdc(String codiceUdc)
    {
        this.codiceUdc = codiceUdc;
    }

    public String getColore()
    {
        return colore;
    }

    public void setColore(String colore)
    {
        this.colore = colore;
    }

    public String getTrasparenza()
    {
        return trasparenza;
    }

    public void setTrasparenza(String trasparenza)
    {
        this.trasparenza = trasparenza;
    }

    public String getSpessoreLinea()
    {
        return spessoreLinea;
    }

    public void setSpessoreLinea(String spessoreLinea)
    {
        this.spessoreLinea = spessoreLinea;
    }

}
