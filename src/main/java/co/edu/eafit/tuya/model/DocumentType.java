package co.edu.eafit.tuya.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Short id;
    private String documentType;

    public DocumentType(String documentType){
        this.documentType = documentType;
    }

    @Override
    public String toString(){
        return documentType;
    }

}
