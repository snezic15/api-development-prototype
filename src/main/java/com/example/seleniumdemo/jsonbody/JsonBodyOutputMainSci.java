package com.example.seleniumdemo.jsonbody;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonBodyOutputMainSci {
    private String title = "";
    private String diaryNo = "";
    private String caseNo = "";
    private String listingDate = "";
    private String status = "";
    private String disposalNature = "";
    private String listingStage = "";
    private String courtNo = "";
    private String[] petitioners = new String[0];
    private String[] respondents = new String[0];
    private String[] padvocates = new String[0];
    private String[] radvocates = new String[0];
    private ArrayList<LinkedHashMap<String, String>> orders = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> process = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> listingDates = new ArrayList<>();
    private String act = "";
    private ArrayList<LinkedHashMap<String, String>> officeReports = new ArrayList<>();
    private String cn = "";
    private String cy = "";
    private String courtID = "";
    private String caseTypeStr = "";
    private ArrayList<LinkedHashMap<String, String>> IA = new ArrayList<>();
    private String[] listingJudges = new String[0];
    private String[] judgements = new String[0];
    private String[] history = new String[0];
    private ArrayList<LinkedHashMap<String, String>> taggedMatters = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> caveats = new ArrayList<>();
    private String listingDateSource = "";
    private String listingRoom = "";
    private String caseNoString = "";
    private String[] listingAdvocates = new String[0];
    private LinkedHashMap<String, String> listing = new LinkedHashMap<>();
    private String[] causeLists = new String[0];
    private LinkedHashMap<String, String> statusGroup = new LinkedHashMap<>();
    private String lastUpdated = "";

    //New
    private String category = "";
    private ArrayList<LinkedHashMap<String, String>> indexing = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> earlierCourtDetails = new ArrayList<>();
    private LinkedHashMap<String, String> courtFees = new LinkedHashMap<>();
    private ArrayList<LinkedHashMap<String, String>> notices = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> defects = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> memos = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> restoration = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> notes = new ArrayList<>();
    private ArrayList<LinkedHashMap<String, String>> appearance = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiaryNo() {
        return diaryNo;
    }

    public void setDiaryNo(String diaryNo) {
        this.diaryNo = diaryNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisposalNature() {
        return disposalNature;
    }

    public void setDisposalNature(String disposalNature) {
        this.disposalNature = disposalNature;
    }

    public String getListingStage() {
        return listingStage;
    }

    public void setListingStage(String listingStage) {
        this.listingStage = listingStage;
    }

    public String getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(String courtNo) {
        this.courtNo = courtNo;
    }

    public String[] getPetitioners() {
        return petitioners;
    }

    public void setPetitioners(String[] petitioners) {
        this.petitioners = petitioners;
    }

    public String[] getRespondents() {
        return respondents;
    }

    public void setRespondents(String[] respondents) {
        this.respondents = respondents;
    }

    public String[] getPadvocates() {
        return padvocates;
    }

    public void setPadvocates(String[] padvocates) {
        this.padvocates = padvocates;
    }

    public String[] getRadvocates() {
        return radvocates;
    }

    public void setRadvocates(String[] radvocates) {
        this.radvocates = radvocates;
    }

    public ArrayList<LinkedHashMap<String, String>> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<LinkedHashMap<String, String>> orders) {
        this.orders = orders;
    }

    public ArrayList<LinkedHashMap<String, String>> getProcess() {
        return process;
    }

    public void setProcess(ArrayList<LinkedHashMap<String, String>> process) {
        this.process = process;
    }

    public ArrayList<LinkedHashMap<String, String>> getListingDates() {
        return listingDates;
    }

    public void setListingDates(ArrayList<LinkedHashMap<String, String>> listingDates) {
        this.listingDates = listingDates;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public ArrayList<LinkedHashMap<String, String>> getOfficeReports() {
        return officeReports;
    }

    public void setOfficeReports(ArrayList<LinkedHashMap<String, String>> officeReports) {
        this.officeReports = officeReports;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public String getCourtID() {
        return courtID;
    }

    public void setCourtID(String courtID) {
        this.courtID = courtID;
    }

    public String getCaseTypeStr() {
        return caseTypeStr;
    }

    public void setCaseTypeStr(String caseTypeStr) {
        this.caseTypeStr = caseTypeStr;
    }

    public ArrayList<LinkedHashMap<String, String>> getIA() {
        return IA;
    }

    public void setIA(ArrayList<LinkedHashMap<String, String>> IA) {
        this.IA = IA;
    }

    public String[] getListingJudges() {
        return listingJudges;
    }

    public void setListingJudges(String[] listingJudges) {
        this.listingJudges = listingJudges;
    }

    public String[] getJudgements() {
        return judgements;
    }

    public void setJudgements(String[] judgements) {
        this.judgements = judgements;
    }

    public String[] getHistory() {
        return history;
    }

    public void setHistory(String[] history) {
        this.history = history;
    }

    public ArrayList<LinkedHashMap<String, String>> getTaggedMatters() {
        return taggedMatters;
    }

    public void setTaggedMatters(ArrayList<LinkedHashMap<String, String>> taggedMatters) {
        this.taggedMatters = taggedMatters;
    }

    public ArrayList<LinkedHashMap<String, String>> getCaveats() {
        return caveats;
    }

    public void setCaveats(ArrayList<LinkedHashMap<String, String>> caveats) {
        this.caveats = caveats;
    }

    public String getListingDateSource() {
        return listingDateSource;
    }

    public void setListingDateSource(String listingDateSource) {
        this.listingDateSource = listingDateSource;
    }

    public String getListingRoom() {
        return listingRoom;
    }

    public void setListingRoom(String listingRoom) {
        this.listingRoom = listingRoom;
    }

    public String getCaseNoString() {
        return caseNoString;
    }

    public void setCaseNoString(String caseNoString) {
        this.caseNoString = caseNoString;
    }

    public String[] getListingAdvocates() {
        return listingAdvocates;
    }

    public void setListingAdvocates(String[] listingAdvocates) {
        this.listingAdvocates = listingAdvocates;
    }

    public LinkedHashMap<String, String> getListing() {
        return listing;
    }

    public void setListing(LinkedHashMap<String, String> listing) {
        this.listing = listing;
    }

    public String[] getCauseLists() {
        return causeLists;
    }

    public void setCauseLists(String[] causeLists) {
        this.causeLists = causeLists;
    }

    public LinkedHashMap<String, String> getStatusGroup() {
        return statusGroup;
    }

    public void setStatusGroup(LinkedHashMap<String, String> statusGroup) {
        this.statusGroup = statusGroup;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated() {
        this.lastUpdated = String.valueOf(java.time.Clock.systemUTC().instant());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<LinkedHashMap<String, String>> getIndexing() {
        return indexing;
    }

    public void setIndexing(ArrayList<LinkedHashMap<String, String>> indexing) {
        this.indexing = indexing;
    }

    public ArrayList<LinkedHashMap<String, String>> getEarlierCourtDetails() {
        return earlierCourtDetails;
    }

    public void setEarlierCourtDetails(ArrayList<LinkedHashMap<String, String>> earlierCourtDetails) {
        this.earlierCourtDetails = earlierCourtDetails;
    }

    public LinkedHashMap<String, String> getCourtFees() {
        return courtFees;
    }

    public void setCourtFees(LinkedHashMap<String, String> courtFees) {
        this.courtFees = courtFees;
    }

    public ArrayList<LinkedHashMap<String, String>> getNotices() {
        return notices;
    }

    public void setNotices(ArrayList<LinkedHashMap<String, String>> notices) {
        this.notices = notices;
    }

    public ArrayList<LinkedHashMap<String, String>> getDefects() {
        return defects;
    }

    public void setDefects(ArrayList<LinkedHashMap<String, String>> defects) {
        this.defects = defects;
    }

    public ArrayList<LinkedHashMap<String, String>> getMemos() {
        return memos;
    }

    public void setMemos(ArrayList<LinkedHashMap<String, String>> memos) {
        this.memos = memos;
    }

    public ArrayList<LinkedHashMap<String, String>> getRestoration() {
        return restoration;
    }

    public void setRestoration(ArrayList<LinkedHashMap<String, String>> restoration) {
        this.restoration = restoration;
    }

    public ArrayList<LinkedHashMap<String, String>> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<LinkedHashMap<String, String>> notes) {
        this.notes = notes;
    }

    public ArrayList<LinkedHashMap<String, String>> getAppearance() {
        return appearance;
    }

    public void setAppearance(ArrayList<LinkedHashMap<String, String>> appearance) {
        this.appearance = appearance;
    }
}