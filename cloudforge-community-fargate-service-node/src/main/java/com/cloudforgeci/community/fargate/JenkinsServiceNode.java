package com.cloudforgeci.community.fargate;


import com.cloudforgeci.api.core.DeploymentContext;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;
import com.cloudforgeci.community.compute.jenkins.Jenkins;

public class JenkinsServiceNode extends Stack {
    public JenkinsServiceNode(final Construct scope, final String id) { this(scope, id, null); }
    public JenkinsServiceNode(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        DeploymentContext ctx = DeploymentContext.from(scope);

        Jenkins.fargate(this, id, ctx);

    }

}
