# PRIDE-Mod Library

PRIDE Modification library (PRIDE-Mod) is a Java API to handle post-translational modifications (PTMs) in proteomics experiments. The library provide a common representation between different PTMs databases such as [UNIMOD](www.ebi.ac.uk/pride/archive/) , [PSI-MOD](http://www.psidev.info/MOD) and the PRIDE Modification Slim Ontology.

It also provide a the corresponding parsers for UNIMOD and PSI-MOD databases. It can be use to retrieve an specific modification using Accessions, Amino Acids, Delta Masses (monoisotopic or average). 

# Main Features
* Support UNIMOD and PSI-MOD databases
* Common representation for all kind of modifications.
* Common API to retrieve modifications based on: Accessions, Amino Acids, Delta Masses, etc.
* Retrieve modifications by String patterns in Names or Descriptions.
* Retrieve Group of Modifications based on common Names or Specificity Groups.

# Availability and Version
* Current version is 2.0.0-SNAPSHOT
* The jara library can be download from the [EBI maven repository]( http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo).

# Getting pride-mod

Maven Dependency
If you wish to include pride-mod in your own Java projects, and you use Maven 2, the following snippets could be useful for you:

- Maven 2 repository definition for pride-mod (and for a host of other EBI libraries):
        
        <repository>
            <id>nexus-ebi-repo</id>
            <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo</url>
        </repository>
        <!-- EBI SNAPSHOT repo -->
        <snapshotRepository>
            <id>nexus-ebi-repo-snapshots</id>
            <url>http://www.ebi.ac.uk/intact/maven/nexus/content/repositories/ebi-repo-snapshots</url>
        </snapshotRepository>

- pride-mod dependency snippet:

        <dependency>
            <groupId>uk.ac.ebi.pride.tools</groupId>
            <artifactId>pride-mod</artifactId>
             <version>x.y.z</version>
        </dependency>


# Getting Help

If you have questions or need additional help, please contact the PRIDE Helpdesk at the EBI: pride-support at ebi.ac.uk (replace at with @).

Please send us your feedback, including error reports, improvement suggestions, new feature requests and any other things you might want to suggest to the PRIDE team.