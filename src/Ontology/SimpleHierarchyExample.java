package Ontology;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.model.*;


import javax.annotation.Nonnull;
import java.io.PrintStream;

@SuppressWarnings("javadoc")
public final class SimpleHierarchyExample {

    private static final int INDENT = 4;
    @Nonnull
    private final OWLReasonerFactory reasonerFactory;
    @Nonnull
    private final OWLOntology ontology;
    private final PrintStream out;

    public SimpleHierarchyExample(@Nonnull OWLReasonerFactory reasonerFactory,
                                  @Nonnull OWLOntology inputOntology) {
        this.reasonerFactory = reasonerFactory;
        ontology = inputOntology;
        out = System.out;
    }

    public static void main(String[] args) throws OWLException,
            InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        String reasonerFactoryClassName = null;
        // We first need to obtain a copy of an
        // OWLOntologyManager, which, as the name
        // suggests, manages a set of ontologies.
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // We load an ontology from the URI specified
        // on the command line
        @SuppressWarnings("null")
        @Nonnull
        String x = "http://www.daml.org/2001/10/office/office";
//        System.out.println(x);
        IRI documentIRI = IRI.create(x);
        // Now load the ontology.
        OWLOntology ontology = manager
                .loadOntologyFromOntologyDocument(documentIRI);
        // Report information about the ontology
        System.out.println("Ontology Loaded...");
        System.out.println("Document IRI: " + documentIRI);
        System.out.println("Ontology : " + ontology.getOntologyID());
        System.out.println("Format      : "
                + manager.getOntologyFormat(ontology));
//        OWLReasonerFactory reasoner=
        // / Create a new SimpleHierarchy object with the given reasoner.
        @SuppressWarnings("null")
        SimpleHierarchyExample simpleHierarchy = new SimpleHierarchyExample((OWLReasonerFactory) Class.forName(reasonerFactoryClassName).newInstance(), ontology);
        // Get Thing
        OWLClass clazz = manager.getOWLDataFactory().getOWLThing();
        System.out.println("Class       : " + clazz);
        // Print the hierarchy below thing
        simpleHierarchy.printHierarchy(clazz);
    }

    /**
     * Print the class hierarchy for the given ontology from this class down,
     * assuming this class is at the given level. Makes no attempt to deal
     * sensibly with multiple inheritance.
     */
    private void printHierarchy(@Nonnull OWLClass clazz) throws OWLException {
        OWLReasoner reasoner = reasonerFactory
                .createNonBufferingReasoner(ontology);
        printHierarchy(reasoner, clazz, 0);
        /* Now print out any unsatisfiable classes */
        for (OWLClass cl : ontology.getClassesInSignature()) {
            assert cl != null;
            if (!reasoner.isSatisfiable(cl)) {
                out.println("XXX: " + labelFor(cl));
            }
        }
        reasoner.dispose();
    }

    private String labelFor(@Nonnull OWLClass clazz) {
        /*
         * Use a visitor to extract label annotations
         */
        LabelExtractor le = new LabelExtractor();
//        for (OWLAnnotation anno : (ontology.getAnnotationAssertionAxioms(clazz.getIRI()))) {
//            anno.accept(le);
//        }
        for (OWLAnnotationAssertionAxiom axiom : ontology.getAnnotationAssertionAxioms(clazz.getIRI())) {
            ((OWLAnnotation)axiom).accept(le);
        }

    //        for (OWLAnnotation annotation : ontology.getAnnotations()) {
    //            annotation.accept(le);
    //        }

        /* Print out the label if there is one. If not, just use the class URI */
        if (le.getResult() != null) {
            return le.getResult();
        } else {
            return clazz.getIRI().toString();
        }
    }

    /**
     * Print the class hierarchy from this class down, assuming this class is at
     * the given level. Makes no attempt to deal sensibly with multiple
     * inheritance.
     */
    private void printHierarchy(@Nonnull OWLReasoner reasoner,
                                @Nonnull OWLClass clazz, int level) throws OWLException {
        /*
         * Only print satisfiable classes -- otherwise we end up with bottom
         * everywhere
         */
        if (reasoner.isSatisfiable(clazz)) {
            for (int i = 0; i < level * INDENT; i++) {
                out.print(" ");
            }
            out.println(labelFor(clazz));
            /* Find the children and recurse */
            for (OWLClass child : reasoner.getSubClasses(clazz, true)
                    .getFlattened()) {
                if (!child.equals(clazz)) {
                    printHierarchy(reasoner, child, level + 1);
                }
            }
        }
    }
}