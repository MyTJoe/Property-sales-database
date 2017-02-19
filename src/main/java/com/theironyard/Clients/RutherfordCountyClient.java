package com.theironyard.Clients;

public class RutherfordCountyClient {
    private String url = "http://web1.mobile311.com/arcgis/rest/services/NorthCarolina/RutherfordCounty/MapServer" +
            "/46/query?where=&text=%&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRe" +
            "l=esriSpatialRelIntersects&relationParam=&outFields=Property_Owner,Land_Sale_Date,Land_Class,Physica" +
            "l_Address,Physical_Address_City,Physical_Address_State,Physical_Address_Zip,Total_Land_Value_Assesse" +
            "d,Total_Building_Value_Assessed,Total_Property_Value,Owner_Mailing_Address_1,Owner_Mailing_Address_C" +
            "ity,Owner_Mailing_Address_State,Owner_Mailing_Address_Zip,Sale_Price&returnGeometry=false&returnTrue" +
            "Curves=false&maxAllowableOffset=&geometryPrecision=&outSR=&returnIdsOnly=false&returnCountOnly=false" +
            "&orderByFields=Land_Sale_Date DESC&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=" +
            "false&gdbVersion=&returnDistinctValues=false&resultOffset=&resultRecordCount=500&f=pjson";
}

//         Property_Owner,
//         Land_Sale_Date,
//         Land_Class,
//         Physical_Address,
//         Physical_Address_City,
//         Physical_Address_State,
//         Physical_Address_Zip,
//         Total_Land_Value_Assessed,
//         Total_Building_Value_Assessed,
//         Total_Property_Value,
//         Owner_Mailing_Address_1,
//         Owner_Mailing_Address_City,
//         Owner_Mailing_Address_State,
//         Owner_Mailing_Address_Zip,
//         Sale_Price