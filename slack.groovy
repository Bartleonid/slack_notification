#!/usr/bin/env groovy
void log(message) {
  manager.listener.logger.println(message)
}
def build_url = manager.envVars['BUILD_URL']
def job_name = manager.envVars['JOB_NAME']
def build_number = manager.envVars['BUILD_NUMBER']
log(build_url)

def buildResult = manager.getResult()
  if ( buildResult == "SUCCESS" ) {
   def request = ["curl", "-k", "-X", "POST", "-H", "Content-Type: application/json", "--data", "{\"text\":\"Job: ${job_name} #${build_number} - SUCCESS ${build_url}\"}", "https://hooks.slack.com/services/some_id/some_id"].execute().text
  }
  else if( buildResult == "FAILURE" ) { 
def request = ["curl", "-k", "-X", "POST", "-H", "Content-Type: application/json", "--data", "{\"text\":\"Job: ${job_name} #${build_number} - FAILURE ${build_url}\" }", "https://hooks.slack.com/services/T025K18QU/some_id/some_id"].execute().text
  }
  else if( buildResult == "UNSTABLE" ) { 
def request = ["curl", "-k", "-X", "POST", "-H", "Content-Type: application/json", "--data", "{\"text\":\"Job: ${job_name} #${build_number} - UNSTABLE ${build_url}\"}", "https://hooks.slack.com/services/T025K18QU/some_id/some_id"].execute().text
  }
  else {
def request = ["curl", "-k", "-X", "POST", "-H", "Content-Type: application/json", "--data", "{\"text\":\"Job: ${job_name} #${build_number} - result was unclear ${build_url}\"}", "https://hooks.slack.com/services/some_id/some_id"].execute().text
  }
