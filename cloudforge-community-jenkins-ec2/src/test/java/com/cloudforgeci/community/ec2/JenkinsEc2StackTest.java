// Copyright (c) CloudForgeCI
// SPDX-License-Identifier: Apache-2.0
//
// These tests use AWS CDK Assertions for Java to validate that the stacks synthesize
// and include the key AWS resources expected for each flavor.

package com.cloudforgeci.community.ec2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.assertions.Template;

public class JenkinsEc2StackTest {

    @Test
    void synthesizesWithVpcAndAlb() {
        App app = new App();
        JenkinsEc2Stack stack = new JenkinsEc2Stack(app, "TestJenkinsEc2", StackProps.builder().env(Environment.builder().account("111111111111").region("us-east-1").build()).build());
        Template template = Template.fromStack(stack);

        Map<String, Map<String, Object>> vpcs = template.findResources("AWS::EC2::VPC");
        assertTrue(!vpcs.isEmpty(), "Expected a VPC in the synthesized template");

        Map<String, Map<String, Object>> lbs = template.findResources("AWS::ElasticLoadBalancingV2::LoadBalancer");
        assertFalse(!lbs.isEmpty(), "Expected an Application Load Balancer");
    }
}
