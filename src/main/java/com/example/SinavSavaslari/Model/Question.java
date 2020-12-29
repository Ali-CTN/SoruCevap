package com.example.SinavSavaslari.Model;

public class Question {

    private String Soru,CevapA,CevapB,CevapC,CevapD,DogruCevap,KategoriId,IsImageQuestion,Resim;

    public Question() {
    }

    public Question(String soru, String cevapA, String cevapB, String cevapC, String cevapD, String dogruCevap, String kategoriId, String isImageQuestion, String resim) {
        Soru = soru;
        CevapA = cevapA;
        CevapB = cevapB;
        CevapC = cevapC;
        CevapD = cevapD;
        DogruCevap = dogruCevap;
        KategoriId = kategoriId;
        IsImageQuestion = isImageQuestion;
        Resim = resim;
    }

    public String getSoru() {
        return Soru;
    }

    public void setSoru(String soru) {
        Soru = soru;
    }

    public String getCevapA() {
        return CevapA;
    }

    public void setCevapA(String cevapA) {
        CevapA = cevapA;
    }

    public String getCevapB() {
        return CevapB;
    }

    public void setCevapB(String cevapB) {
        CevapB = cevapB;
    }

    public String getCevapC() {
        return CevapC;
    }

    public void setCevapC(String cevapC) {
        CevapC = cevapC;
    }

    public String getCevapD() {
        return CevapD;
    }

    public void setCevapD(String cevapD) {
        CevapD = cevapD;
    }

    public String getDogruCevap() {
        return DogruCevap;
    }

    public void setDogruCevap(String dogruCevap) {
        DogruCevap = dogruCevap;
    }

    public String getKategoriId() {
        return KategoriId;
    }

    public void setKategoriId(String kategoriId) {
        KategoriId = kategoriId;
    }

    public String getIsImageQuestion() {
        return IsImageQuestion;
    }

    public void setIsImageQuestion(String isImageQuestion) {
        IsImageQuestion = isImageQuestion;
    }

    public String getResim() {
        return Resim;
    }

    public void setResim(String resim) {
        Resim = resim;
    }
}
