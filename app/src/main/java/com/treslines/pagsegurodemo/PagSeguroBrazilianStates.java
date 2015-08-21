package com.treslines.pagsegurodemo;

/**
 * Use this enum to define the brazilian's states<br/>
 * <br/>Author: Ricardo Ferreria, 18/08/2015
 */
public enum PagSeguroBrazilianStates {

    /**
     * Name: Acre, Initials: AC
     */
    ACRE("Acre", "AC"),
    /**
     * Name: Alagoas, Initials: AL
     */
    ALAGOAS("Alagoas", "AL"),
    /**
     * Name: Amapá, Initials: AP
     */
    AMAPA("Amapá", "AP"),
    /**
     * Name: Amazonas, Initials: AM
     */
    AMAZONAS("Amazonas", "AM"),
    /**
     * Name: Bahia, Initials: BA
     */
    BAHIA("Bahia", "BA" ),
    /**
     * Name: Ceará, Initials: CE
     */
    CEARA("Ceará", "CE" ),
    /**
     * Name: Distrito Federal, Initials: DF
     */
    DISTRITOFEDERAL("Distrito Federal", "DF"),
    /**
     * Name: Espírito Santo, Initials: ES
     */
    ESPIRITOSANTO("Espírito Santo", "ES" ),
    /**
     * Name: Goiás, Initials: GO
     */
    GOIAS("Goiás", "GO"),
    /**
     * Name: Maranhão, Initials: MA
     */
    MARANHAO("Maranhão", "MA" ),
    /**
     * Name: Mato Grosso, Initials: MT
     */
    MATOGROSSO("Mato Grosso", "MT"),
    /**
     * Name: Mato Grosso do Sul, Initials: MS
     */
    MATOGROSSODOSUL("Mato Grosso do Sul", "MS" ),
    /**
     * Name: Minas Gerais, Initials: MG
     */
    MINASGERAIS("Minas Gerais", "MG"),
    /**
     * Name: Pará, Initials: PA
     */
    PARA("Pará", "PA" ),
    /**
     * Name: Paraíba, Initials: PB
     */
    PARAIBA("Paraíba", "PB"),
    /**
     * Name: Paraná, Initials: PR
     */
    PARANA("Paraná", "PR" ),
    /**
     * Name: Pernambuco, Initials: PE
     */
    PERNAMBUCO("Pernambuco", "PE"),
    /**
     * Name: Piauí, Initials: PI
     */
    PIAUI("Piauí", "PI"),
    /**
     * Name: Rio de Janeiro, Initials: RJ
     */
    RIODEJANEIRO("Rio de Janeiro", "RJ" ),
    /**
     * Name: Rio Grande do Norte, Initials: RN
     */
    RIOGRANDEDONORTE("Rio Grande do Norte", "RN" ),
    /**
     * Name: Rio Grande do Sul, Initials: RS
     */
    RIOGRANDEDOSUL("Rio Grande do Sul", "RS"),
    /**
     * Name Rondônia, Silga: RO
     */
    RONDONIA("Rondônia", "RO" ),
    /**
     * Name: Roraima, Initials: RO
     */
    RORAIMA("Roraima", "RR"),
    /**
     * Name: Santa Catarina, Initials: SC
     */
    SANTACATARINA("Santa Catarina", "SC"),
    /**
     * Name: São Paulo, Initials: SP
     */
    SAOPAULO("São Paulo", "SP" ),
    /**
     * Name: Sergipe, Initials: SE
     */
    SERGIPE("Sergipe", "SE"),
    /**
     * Name: Tocantins, Initials: TO
     */
    TOCANTINS("Tocantins", "TO");
    private String name;
    private String initials;

    private PagSeguroBrazilianStates(String name, String initials) {
        this.name = name;
        this.initials = initials;
    }

    /**
     * @return the full state name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the state's initials composed of two capital letters
     */
    public String getInitials() {
        return initials;
    }
}
