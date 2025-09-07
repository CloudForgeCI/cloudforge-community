// Copyright (c) CloudForgeCI
// SPDX-License-Identifier: Apache-2.0
//
// These tests use AWS CDK Assertions for Java to validate that the stacks synthesize
// and include the key AWS resources expected for each flavor.

package com.cloudforgeci.community.fargate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.assertions.Template;

public class JenkinsFargateEfsEcsDomainSslStackTest {

    @Test
    void synthesizesWithCertDnsAndCoreFargateBits() {
        App app = new App();
        app.getNode().setContext("domainZone", "example.com");
        app.getNode().setContext("domainName", "jenkins.example.com");

        JenkinsFargateEfsEcsDomainSslStack stack = new JenkinsFargateEfsEcsDomainSslStack(app, "TestJenkinsFargateSsl", StackProps.builder().env(Environment.builder().account("111111111111").region("us-east-1").build()).build());
        Template template = Template.fromStack(stack);

        assertFalse(template.findResources("AWS::CertificateManager::Certificate").isEmpty(),
                    "Expected an ACM certificate");
        assertFalse(template.findResources("AWS::Route53::RecordSet").isEmpty(),
                    "Expected a Route53 record");

        assertFalse(template.findResources("AWS::ECS::Cluster").isEmpty(), "Expected an ECS Cluster");
        assertFalse(template.findResources("AWS::ECS::TaskDefinition").isEmpty(), "Expected a TaskDefinition");
        assertFalse(template.findResources("AWS::ECS::Service").isEmpty(), "Expected an ECS Service");
        assertFalse(template.findResources("AWS::EFS::FileSystem").isEmpty(), "Expected an EFS FileSystem");

        assertFalse(template.findResources("AWS::ElasticLoadBalancingV2::LoadBalancer").isEmpty(),
                    "Expected an Application Load Balancer");
    }
}
