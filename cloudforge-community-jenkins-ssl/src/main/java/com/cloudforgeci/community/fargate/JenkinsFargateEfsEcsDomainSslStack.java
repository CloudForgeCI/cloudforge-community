package com.cloudforgeci.community.fargate;

import com.cloudforgeci.community.core.jenkins.JenkinsFargateEfsEcsBuilder;
import com.cloudforgeci.core.api.DeploymentContext;
import com.cloudforgeci.core.api.JenkinsConfig;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class JenkinsFargateEfsEcsDomainSslStack extends Stack {
    public JenkinsFargateEfsEcsDomainSslStack(final Construct scope, final String id) { this(scope, id, null); }
    public JenkinsFargateEfsEcsDomainSslStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        DeploymentContext ctx = DeploymentContext.from(scope);

        String zone = (String) getNode().tryGetContext("domainZone");
        String name = (String) getNode().tryGetContext("domainName");
        JenkinsConfig cfg = new JenkinsConfig(id, true, zone == null ? "example.com" : zone, name == null ? "jenkins.example.com" : name, props);
        JenkinsFargateEfsEcsBuilder.create(scope, id, cfg, ctx);
    }

}
