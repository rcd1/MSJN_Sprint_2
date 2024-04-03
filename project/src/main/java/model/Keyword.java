/**
 * An enumeration class for keywords
 */
package model;
public enum Keyword {
    Honors,
    VSR,
    GSS,
    AIU,
    CRW,
    SCI,
    ARP,
    CMS,
    GFL,
    GHS,
    PR,
    MR,
    LAE,
    LSE, 
    
    // Everything past this point is a hacky solution used for "this or that" classes inside a major map
    COP1("CHEM 111 or PHYS 111"), // Chem or Physics 1 
    COP2("CHEM 112 or PHYS 112"), // Chem or Physics 2
    SPCHC("SPCH 140 or SPCH 145 or SPCH 230"), // SPCH 140 or SPCH 145 or SPCH 230
    ENGLW ("ENGL 462 or ENGL 463"), // Technical writing or business writing 

    AP0(ApplicationID.UNDECLARED),
    AP1(ApplicationID.SCIENCE),
    AP2(ApplicationID.MATH),
    AP3(ApplicationID.DIGITALDESIGN),
    AP4(ApplicationID.ROBOTICS),
    AP5(ApplicationID.SPEECH),
    CSCEME("CSCE Major Elective");

    private Keyword() {
        title = "none";
    }

    private Keyword(String title) {
        this.title = title;
    }

    private Keyword(ApplicationID appid) {
        this.title = appid.getName();
    }

    public String getTitle() {
        return title;
    }

    public ApplicationID getKeyword() {
        return appid;
    }

    String title;
    ApplicationID appid;

}
