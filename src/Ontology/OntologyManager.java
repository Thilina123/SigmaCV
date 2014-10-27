package Ontology;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;

/**
 * Created by Thilina on 10/27/2014.
 */
public class OntologyManager {
    public static void main(String[] args) {
        OntologyManager owl = new OntologyManager();
        owl.Load();
    }

    public void Load() {
        try {
            OWLOntologyManager m = OWLManager.createOWLOntologyManager();
            OWLOntology o = m.loadOntologyFromOntologyDocument(new File("src\\Ontology\\office.owl"));
            System.out.println(o.toString());
//            SimpleHierarchyExample hierarchy=new SimpleHierarchyExample();
//            OWLAnnotation
//            OWLDataFactory df=m.get
//            for (OWLAxiom owlAxiom : o.getAxioms()) {
//                System.out.println(owlAxiom.toString());
//            }
//            ;
//            System.out.println(o.);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
    }
}
