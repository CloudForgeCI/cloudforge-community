package com.cloudforgeci.community.fargate;

import com.cloudforgeci.api.api.JenkinsConfig;
import com.cloudforgeci.api.core.DeploymentContext;
import com.cloudforgeci.community.compute.jenkins.JenkinsFargateEfsEcsBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class JenkinsFargateEfsEcsStack extends Stack {
    public JenkinsFargateEfsEcsStack(final Construct scope, final String id) { this(scope, id, null); }
    public JenkinsFargateEfsEcsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        DeploymentContext ctx = DeploymentContext.from(scope);
        JenkinsConfig cfg = new JenkinsConfig(id, false, null, null, props);
        JenkinsFargateEfsEcsBuilder.create(this, id, cfg, ctx);
    }

}
