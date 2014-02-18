/**
 * Copyright (C) 2013 Petrozavodsk State University.
 *
 * Thil file is part of WebD.
 */  

package ru.petrsu.imo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

public class Student {

    private String studentName = "";
    private String adviserName = "";
    private String workTitle = "";
    private String year = "";
    private String course = "";
    private String group = "";
    private boolean hasIndex = false;
    private String linkToReport = "-";
    private String linkToSemiReport = "-";
    private String linkToPres = "-";
    private String linkToInterimPres = "-";
    private String linkToPath = "-";
    private String path;

    /**
     * Parses and extracts data from specified index.xml file.
     *
     * @param linkIndexXml - path to the index.xml file.
     * @param searchSN - student name for search.
     * @param searchKP - key phrase for search
     */ 
    public Student(String linkIndexXml) {
	this.path = linkIndexXml;
	getXMLData(linkIndexXml); 
    } 
    
    /**
     * Parses and extracts data from specified index.xml file.
     *
     * @param linkIndexXml - path to the index.xml file.
     */ 
    public void getXMLData(String linkIndexXml)
    {
        linkIndexXml += "/index.xml";
        
        File xmlFile = new File(linkIndexXml);
        
        if (xmlFile.exists()) {
	    hasIndex = true;
	    // Gets data from the xml file index.xml
            try
            {  
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

                Document document = docBuilder.parse(xmlFile);
                document.getDocumentElement().normalize();

                NodeList nodes = document.getElementsByTagName("name").item(0).getChildNodes();
                Node node = (Node) nodes.item(0);
                studentName = node.getNodeValue();

                nodes = document.getElementsByTagName("adviser");
                for (int i = 0; i < nodes.getLength(); i++) 
                {
                    //Node node = nodes.item(i);
                    node = nodes.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) 
                    {
                        Element element = (Element) node;
                        adviserName = getValue("name", element);
                    }
                }


                nodes = document.getElementsByTagName("title").item(0).getChildNodes();
                node = (Node) nodes.item(0);
                workTitle = node.getNodeValue();
//		workTitle = "<a href=\"http://kappa.cs.karelia.ru/p/" + 
//			path.replace(Config.PATH_TO_PROJECTS + "/", "") + "/final/presentation/slides.pdf\" target=\"_blank\">" + 
//			workTitle + "</a>";

            }
            catch (Exception e)
            {
		workTitle = "Файл index.xml не может быть разобран (" + e.getMessage() + ").";
		studentName = path.substring(path.lastIndexOf('/') + 1);
		adviserName = "---";
            }

        } else {
            workTitle = "Отсутствует файл index.xml";
            studentName = path.substring(path.lastIndexOf('/') + 1);
            adviserName = "---";
        }

	// Defines links to files.
	linkToReport = getLink(Config.DIRECTORY_FINAL_REPORT, Config.FILE_FINAL_REPORT);
	linkToSemiReport = getLink(Config.DIRECTORY_INTERIM_REPORT, Config.FILE_INTERIM_REPORT);
	linkToInterimPres = getLink(Config.DIRECTORY_INTERIM_PRES, Config.FILE_INTERIM_PRES);
	linkToPres = getLink(Config.DIRECTORY_FINAL_PRES, Config.FILE_FINAL_PRES);
	linkToPath = "http://kappa.cs.karelia.ru/p/" + path.replace(Config.PATH_TO_PROJECTS + "/", "");
	
	// Defines year, course and group from path.
	String without = path.replace(Config.PATH_TO_PROJECTS + "/", "");
	year = without.substring(0, without.indexOf('/'));
	without = path.replace(Config.PATH_TO_PROJECTS + "/" + year + "/", "");
	course = without.substring(0, without.indexOf('/'));
	without = path.replace(Config.PATH_TO_PROJECTS + "/" + year + "/" + course + "/", "");
	group = without.substring(0, without.indexOf('/'));
    }


    
    // Setters and getters
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }
    
    public void setAdviserName(String adviserName) {
        this.adviserName = adviserName;
    }

    public String getAdviserName() {
        return adviserName;
    }
        
    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public String getCourse() {
        return this.course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isIndex() {
        return this.hasIndex;
    }
    public void setIndex(boolean hasIndex) {
        this.hasIndex = hasIndex;
    }

    public String getLinkToReport() {
        return this.linkToReport;
    }
    public void setLinkToReport(String linkToReport) {
        this.linkToReport = linkToReport;
    }

    public String getLinkToPath() {
        return this.linkToPath;
    }
    public void setLinkToPath(String linkToPath) {
        this.linkToPath = linkToPath;
    }

    public String getLinkToSemiReport() {
        return this.linkToSemiReport;
    }
    public void setLinkToSemiReport(String linkToSemiReport) {
        this.linkToSemiReport = linkToSemiReport;
    }

    public String getLinkToInterimPres() {
        return this.linkToInterimPres;
    }
    public void setLinkToInterimPres(String linkToInterimPres) {
        this.linkToInterimPres = linkToInterimPres;
    }

    public String getLinkToPres() {
        return this.linkToPres;
    }
    public void setLinkToPres(String linkToPres) {
        this.linkToPres = linkToPres;
    }

    /**
     * Gets value from the specified element due to specified tag.
     */ 
    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    /**
     * Returns link due to specified directory and file.
     */ 
    private String getLink(String directory, String file) {
        String pathToPres = path + "/" + directory + "/" + file;
        File pres = new File(pathToPres);
        if (pres.exists()) {
            return "<a href=\"http://kappa.cs.karelia.ru/p/"
		    + path.replace(Config.PATH_TO_PROJECTS + "/", "") + "/"
		    + directory + "/" + file + "\" target=\"_blank\">"
		    + "+</a>";
	}
	return "-";
    }
}
