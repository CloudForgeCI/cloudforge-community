package com.cloudforgeci.community.ec2;

import com.cloudforgeci.community.core.jenkins.JenkinsEc2Builder;
import com.cloudforgeci.core.api.JenkinsConfig;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class JenkinsEc2DomainSslStack extends Stack {
    public JenkinsEc2DomainSslStack(final Construct scope, final String id) { this(scope, id, null); }
    public JenkinsEc2DomainSslStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        String zone = (String) getNode().tryGetContext("domainZone");
        String name = (String) getNode().tryGetContext("domainName");
        JenkinsConfig cfg = new JenkinsConfig(id, true, zone == null ? "example.com" : zone, name == null ? "jenkins.example.com" : name, props);
        JenkinsEc2Builder.create(this, id, cfg, null);
    }
}
