package model;

/**
 *
 * @author andre
 */
public class Empresa {
    
    private int Codigo;
    private String RazaoSocial;
    private String NomeResumido;
    private String Cnpj;
    private String InscEstadual;
    private String Telefone;
    private String WhatSapp;
    private String Logradouro;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private int Cep;
    private String Ibge;
    private String InscMunicipal;
    private String Cnae;
    private String Responsavel;
    private String CpfRespon;
    private String RegimeTrib;
    private String Logomarca;
    
    public Empresa(int Codigo,String RazaoSocial,String NomeResumido,String Cnpj,String InscEstadual,String Telefone,String WhatSapp,String Logradouro,String Numero,String Bairro,String Cidade,String Estado,int Cep,String Ibge,String InscMunicipal,
                   String Cnae,String Responsavel,int CpfRespon,String RegimeTrib,String Logomarca){
        this.Codigo = Codigo;
        this.RazaoSocial = RazaoSocial;
        this.NomeResumido = NomeResumido;
        this.Cnpj = Cnpj;
        this.InscEstadual = InscEstadual;
        this.Telefone = Telefone;
        this.WhatSapp = WhatSapp;
        this.Logradouro = Logradouro;
        this.Numero = Numero;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Cep = Cep;
        this.Ibge = Ibge;
        this.InscMunicipal = InscMunicipal;
        this.Cnae = Cnae;
        this.Responsavel = Responsavel;
        this.CpfRespon = this.CpfRespon;
        this.RegimeTrib = RegimeTrib;
        this.Logomarca = Logomarca;        
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getNomeResumido() {
        return NomeResumido;
    }

    public void setNomeResumido(String NomeResumido) {
        this.NomeResumido = NomeResumido;
    }

    public String getInscEstadual() {
        return InscEstadual;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public void setInscEstadual(String InscEstadual) {
        this.InscEstadual = InscEstadual;
    }

    public String getWhatSapp() {
        return WhatSapp;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public void setWhatSapp(String WhatSapp) {
        this.WhatSapp = WhatSapp;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getCep() {
        return Cep;
    }

    public void setCep(int Cep) {
        this.Cep = Cep;
    }

    public String getIbge() {
        return Ibge;
    }

    public void setIbge(String Ibge) {
        this.Ibge = Ibge;
    }

    public String getInscMunicipal() {
        return InscMunicipal;
    }

    public void setInscMunicipal(String InscMunicipal) {
        this.InscMunicipal = InscMunicipal;
    }

    public String getCnae() {
        return Cnae;
    }

    public void setCnae(String Cnae) {
        this.Cnae = Cnae;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String Responsavel) {
        this.Responsavel = Responsavel;
    }

    public String getCpfRespon() {
        return CpfRespon;
    }

    public void setCpfRespon(String CpfRespon) {
        this.CpfRespon = CpfRespon;
    }

    public String getRegimeTrib() {
        return RegimeTrib;
    }

    public void setRegimeTrib(String RegimeTrib) {
        this.RegimeTrib = RegimeTrib;
    }

    public String getLogomarca() {
        return Logomarca;
    }

    public void setLogomarca(String Logomarca) {
        this.Logomarca = Logomarca;
    }
    
}
