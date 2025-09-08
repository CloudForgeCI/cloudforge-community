package com.cloudforgeci.community.ec2;

import com.cloudforgeci.community.core.jenkins.JenkinsEc2Builder;
import com.cloudforgeci.core.api.JenkinsConfig;


import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class JenkinsEc2Stack extends Stack {
    public JenkinsEc2Stack(final Construct scope, final String id) { this(scope, id, null); }
    public JenkinsEc2Stack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        JenkinsConfig cfg = new JenkinsConfig(id, false, null, null, props);
        JenkinsEc2Builder.create(this, id, cfg, null);
    }
}
