package it.reexon.reexon.lib.json.dtos;

import java.util.ArrayList;
import java.util.List;


public class MapRequestDTO
{
    private String idApplicativo = "idApplicativo";

    private String externalUsername = "externalUsername";

    private List<MapRequestUdcDTO> udcList = new ArrayList<MapRequestUdcDTO>()
    {
        private static final long serialVersionUID = 1L;

        {
            add(new MapRequestUdcDTO("U8161", "#FF0000", "0.7", "1"));
            add(new MapRequestUdcDTO("U12040", "#FFF033", "0.9", "1"));
        }
    };

    public String getIdApplicativo()
    {
        return idApplicativo;
    }

    public void setIdApplicativo(String idApplicativo)
    {
        this.idApplicativo = idApplicativo;
    }

    public String getExternalUsername()
    {
        return externalUsername;
    }

    public void setExternalUsername(String externalUsername)
    {
        this.externalUsername = externalUsername;
    }

    public List<MapRequestUdcDTO> getUdcList()
    {
        return udcList;
    }

    public void setUdcList(List<MapRequestUdcDTO> udcList)
    {
        this.udcList = udcList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "MapRequestDTO [idApplicativo = " + idApplicativo + ", externalUsername=" + externalUsername + 
                ", udcList=" + udcList + "]";
    }

}
