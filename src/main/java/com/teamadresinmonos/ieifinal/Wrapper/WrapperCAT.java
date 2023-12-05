package com.teamadresinmonos.ieifinal.Wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamadresinmonos.ieifinal.Entities.CentroCAT;
import com.teamadresinmonos.ieifinal.Util.Config;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WrapperCAT {
    public static List<String> kebab(String filename){
        try {
            System.out.println("Wrapper CAT: " + Config.getDataLocation() + filename);
            ObjectMapper mapper = new ObjectMapper();
            Document domDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(Config.getDataLocation() + filename));
            NodeList rows = domDocument.getElementsByTagName("row");
            List<String> jsonsTransformados = new ArrayList<>();

            //1 pq asi ignoramos el <response>
            for (int i = 1; i < rows.getLength(); i++) {
                Node node = rows.item(i);
                NodeList nodosHijos = node.getChildNodes();
                removeTextNodes(nodosHijos);
                CentroCAT centro = new CentroCAT();
                //begin dump
                System.out.println("WRAPPER CAT: " + i);
                centro.setCodiCentre(getNodeByTagName(nodosHijos, "codi_centre").getTextContent());
                centro.setDenominaciCompleta(getNodeByTagName(nodosHijos,"denominaci_completa").getTextContent());
                centro.setNomNaturalesa(getNodeByTagName(nodosHijos,"nom_naturalesa").getTextContent());
                centro.setNomTitularitat(getNodeByTagName(nodosHijos,"nom_titularitat").getTextContent());
                centro.setAdreca(getNodeByTagName(nodosHijos,"adre_a").getTextContent());
                centro.setCodiPostal(getNodeByTagName(nodosHijos,"codi_postal").getTextContent());
                centro.setNomComarca(getNodeByTagName(nodosHijos,"nom_comarca").getTextContent());
                centro.setCodiComarca(getNodeByTagName(nodosHijos,"codi_comarca").getTextContent());
                centro.setCodiMunicipi5Digits(getNodeByTagName(nodosHijos,"codi_municipi_5_digits").getTextContent());
                centro.setCodiMunicipi6Digits(getNodeByTagName(nodosHijos,"codi_municipi_6_digits").getTextContent());
                centro.setNomMunicipi(getNodeByTagName(nodosHijos,"nom_municipi").getTextContent());
                centro.setCodiDistricteMunicipal(getNodeByTagName(nodosHijos,"codi_districte_municipal").getTextContent());
                centro.setCoordenadesUtmX(Double.parseDouble(getNodeByTagName(nodosHijos,"coordenades_utm_x").getTextContent()));
                centro.setCoordenadesUtmY(Double.parseDouble(getNodeByTagName(nodosHijos,"coordenades_utm_y").getTextContent()));
                centro.setCoordenadesGeoX(Double.parseDouble(getNodeByTagName(nodosHijos,"coordenades_geo_x").getTextContent()));
                centro.setCoordenadesGeoY(Double.parseDouble(getNodeByTagName(nodosHijos,"coordenades_geo_y").getTextContent()));
                centro.setEstudis(getNodeByTagName(nodosHijos,"estudis").getTextContent());
                centro.setGeoreferencia(getNodeByTagName(nodosHijos,"georefer_ncia").getTextContent());
                //end dump
                jsonsTransformados.add(mapper.writeValueAsString(centro));
            }
            return jsonsTransformados;
        }catch(Exception e) {
            System.out.println("EXCEPCION EN CAT: " + e);
            return new ArrayList<>();
        }
    }


    public static Node getNodeByTagName(NodeList nodeList, String tagName) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName)) {
                return node;
            }
        }
        return null;
    }

    private static void removeTextNodes(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty()) {
                node.getParentNode().removeChild(node);
            }

            if (node.hasChildNodes()) {
                removeTextNodes(node.getChildNodes());
            }
        }
    }
}
