package com.treslines.pagsegurodemo;

/**
 * Use this enum to define the supported pagseguro's DDD<br/>
 * <br/>Author: Ricardo Ferreria, 18/08/2015
 */
public enum PagSeguroAreaCode {

    DDD11("11","São Paulo"),
    DDD12("12","São Paulo"),
    DDD13("13","São Paulo"),
    DDD14("14","São Paulo"),
    DDD15("15","São Paulo"),
    DDD16("16","São Paulo"),
    DDD17("17","São Paulo"),
    DDD18("18","São Paulo"),
    DDD19("19","São Paulo"),
    DDD21("21","Rio de Janeiro"),
    DDD22("22","Rio de Janeiro"),
    DDD24("24","Rio de Janeiro"),
    DDD27("27","Espírito Santo"),
    DDD28("28","Espírito Santo"),
    DDD31("31","Minas Gerais"),
    DDD32("32","Minas Gerais"),
    DDD33("33","Minas Gerais"),
    DDD34("34","Minas Gerais"),
    DDD35("35","Minas Gerais"),
    DDD37("37","Minas Gerais"),
    DDD38("38","Minas Gerais"),
    DDD41("41","Paraná"),
    DDD42("42","Paraná"),
    DDD43("43","Paraná"),
    DDD44("44","Paraná"),
    DDD45("45","Paraná"),
    DDD46("46","Paraná"),
    DDD47("47","Santa Catarina"),
    DDD48("48","Santa Catarina"),
    DDD49("49","Santa Catarina"),
    DDD51("51","Rio Grande do Sul"),
    DDD53("53","Rio Grande do Sul"),
    DDD54("54","Rio Grande do Sul"),
    DDD55("55","Rio Grande do Sul"),
    DDD61("61","Distrito Federal"),
    DDD62("62","Goiás"),
    DDD63("63","Tocantins"),
    DDD64("64","Goiás"),
    DDD65("65","Mato Grosso"),
    DDD66("66","Mato Grosso"),
    DDD67("67","Mato Grosso do Sul"),
    DDD68("68","Acre"),
    DDD69("69","Rondônia"),
    DDD71("71","Bahia"),
    DDD73("73","Bahia"),
    DDD74("74","Bahia"),
    DDD75("75","Bahia"),
    DDD77("77","Bahia"),
    DDD79("79","Sergipe"),
    DDD81("81","Pernambuco"),
    DDD82("82","Alagoas"),
    DDD83("83","Paraíba"),
    DDD84("84","Rio Grande do Norte"),
    DDD85("85","Ceará"),
    DDD86("86","Piauí"),
    DDD87("87","Pernambuco"),
    DDD88("88","Ceará"),
    DDD89("89","Piauí"),
    DDD91("91","Pará"),
    DDD92("92","Amazonas"),
    DDD93("93","Pará"),
    DDD94("94","Pará"),
    DDD95("95","Roraima"),
    DDD96("96","Amapá"),
    DDD97("97","Amazonas"),
    DDD98("98","Maranhão"),
    DDD99("99","Maranhão");

    private String areaCode;
    private String correspondingState;

    private PagSeguroAreaCode(String areaCode, String correspondingState) {
        this.areaCode = areaCode;
        this.correspondingState = correspondingState;
    }

    /**
     * @return the representing ddd code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @return the correspondingState state
     */
    public String getCorrespondingState() {
        return correspondingState;
    }
}
