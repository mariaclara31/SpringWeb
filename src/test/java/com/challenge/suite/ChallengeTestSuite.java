package com.challenge.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccelerationControllerTest.class, CandidateControllerTest.class,
        ChallengeControllerTest.class, CompanyControllerTest.class,
        SubmissionControllerTest.class, UserControllerTest.class})
public class ChallengeTestSuite {
}
