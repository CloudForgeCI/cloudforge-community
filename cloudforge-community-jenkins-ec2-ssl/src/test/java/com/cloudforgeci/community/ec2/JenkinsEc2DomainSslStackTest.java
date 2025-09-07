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

public class JenkinsEc2DomainSslStackTest {

    @Test
    void synthesizesWithCertAndDnsRecords() {
        App app = new App();
        // These context values mirror the expected defaults but we set them explicitly for clarity
        app.getNode().setContext("domainZone", "example.com");
        app.getNode().setContext("domainName", "jenkins.example.com");

        JenkinsEc2DomainSslStack stack = new JenkinsEc2DomainSslStack(app, "TestJenkinsEc2Ssl", StackProps.builder().env(Environment.builder().account("111111111111").region("us-east-1").build()).build());
        Template template = Template.fromStack(stack);

        Map<String, Map<String, Object>> certs = template.findResources("AWS::CertificateManager::Certificate");
        assertFalse(certs.isEmpty(), "Expected an ACM certificate");

        Map<String, Map<String, Object>> records = template.findResources("AWS::Route53::RecordSet");
        assertFalse(records.isEmpty(), "Expected at least one Route53 record");
    }
}
