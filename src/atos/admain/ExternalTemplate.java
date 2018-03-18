package atos.admain;

import java.util.HashMap;

public class ExternalTemplate {
    HashMap<String, Object> external;

    ExternalTemplate(){
        external = new HashMap<String, Object>();
        external.put("ProposalReference", "");
        external.put("DateOfProposal", "");
        external.put("SolutionIntroduction", "");
        external.put("SolutionDescription", "");
        external.put("InScope", "");
        external.put("OutOfScope", "");
        external.put("CustomerResponsibilities", "");
        external.put("AssessmentOfProposedSolutionAgainstRequirements", "");
        external.put("Risks", "");
        external.put("Assumptions", "");
        external.put("Issues", "");
        external.put("Dependencies", "");
    }
}
