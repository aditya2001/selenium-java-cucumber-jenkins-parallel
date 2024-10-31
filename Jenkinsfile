import groovy.json.JsonOutput

pipeline {
    //The agent section specifies where the entire Pipeline, or a specific stage,
    //will execute in the Jenkins environment depending on where the agent section is placed.
    agent any

    triggers {
        parameterizedCron('''
        0 8 * * * %CROSSBROWSER=false;BROWSER=chrome;ENV=uat
        ''')
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'HOURS')
        ansiColor('xterm')
    }

//     parameters {
//          choice(name: 'CROSSBROWSER', choices: ['false', 'true'], description: 'Cross Browser Testing')
//          choice(name: 'BROWSER', choices: ['chrome', 'edge', 'firefox'], description: 'Pick the web browser you want to use to run your scripts')
//          choice(name: 'ENV', choices: ['uat', 'qa', 'dev'], description: 'Pick the env against which you need to run test')
//     }


    stages {
       stage('Parameters'){
                  steps {
                      script {
                      properties([
                              parameters([
                                  [$class: 'ChoiceParameter',
                                      choiceType: 'PT_SINGLE_SELECT',
                                      description: 'Select cross browser for testing',
                                      filterable: false,
                                      name: 'CROSSBROWSER',
                                      script: [
                                          $class: 'GroovyScript',
                                          fallbackScript: [
                                              classpath: [],
                                              sandbox: false,
                                              script:
                                                  "return['Could not get the cross browser value']"
                                          ],
                                          script: [
                                              classpath: [],
                                              sandbox: false,
                                              script:
                                                  "return['false','true']"
                                          ]
                                      ]
                                  ],
                                  [$class: 'CascadeChoiceParameter',
                                      choiceType: 'PT_SINGLE_SELECT',
                                      description: 'Select the browser values from the dropdown List',
                                      name: 'BROWSER',
                                      referencedParameters: 'CROSSBROWSER',
                                      script:
                                          [$class: 'GroovyScript',
                                          fallbackScript: [
                                                  classpath: [],
                                                  sandbox: false,
                                                  script: "return['Could not get browser value']"
                                                  ],
                                          script: [
                                                  classpath: [],
                                                  sandbox: false,
                                                  script: '''
                                                  if (CROSSBROWSER.equals("true")){
                                                      return["All"]
                                                  }
                                                  else if(CROSSBROWSER.equals("false")){
                                                      return["chrome", "firefox"]
                                                  }
                                                   '''
                                                  ]
                                          ]
                                  ],
                                  [$class: 'ChoiceParameter',
                                     choiceType: 'PT_SINGLE_SELECT',
                                     description: 'Select env for testing',
                                     filterable: false,
                                     name: 'ENV',
                                     script:
                                         [$class: 'GroovyScript',
                                         fallbackScript: [
                                              classpath: [],
                                              sandbox: false,
                                              script:
                                                 "return['Could not get the env value']"
                                              ],
                                         script: [
                                                 classpath: [],
                                                 sandbox: false,
                                                 script:
                                                 "return['uat','int']"
                                                 ]
                                         ]
                                  ]


                              ])
                          ])
                      }
                  }
              }
        stage('Building'){
           steps {
             echo "Building the application"
//               checkout scmGit(
//                     branches: [[name: "master"]],
//                     userRemoteConfigs: [[credentialsId: 'ssh-keys',
//                         url: 'git@github.com:aditya2001/selenium-java-cucumber.git']])
             }
          }
        stage('Testing on single browser'){
          when {
              expression {
                  return params.CROSSBROWSER == 'false'
                }
            }
          steps{
            echo "Testing on ${params.BROWSER}"
            bat "mvn test -DsuiteXmlFile=testng.xml -Dbrowser=${params.BROWSER} -Denv=${params.ENV}"
          }
        }

        stage('Cross Browser Testing'){
           when {
             expression {
                return params.CROSSBROWSER == 'true'
              }
             }
           steps{
              bat "mvn test -DsuiteXmlFile=crossbrowser-testng.xml -Denv=${params.ENV}"
           }
        }

//         stage('Cross Browser Parallel Testing'){
//            when {
//              expression {
//                 return params.CROSSBROWSER == 'true'
//                   }
//                 }
//
//              parallel {
//                 stage('chrome test') {
//                    agent any
//                     steps {
//                          bat "mvn test -DsuiteXmlFile=testng.xml -Dbrowser=chrome -Denv=${params.ENV}"
//                     }
//                 }
//                 stage('firefox test') {
//                     agent any
//                      steps {
//                           bat "mvn test -DsuiteXmlFile=testng.xml -Dbrowser=firefox -Denv=${params.ENV}"
//                      }
//                 }
//              }
//         }

        stage('Deploying'){
        steps{
          echo "Deploying"
        }
      }
     }

        post {
             always {
                 archiveArtifacts artifacts: "test output/PdfReport/ExtentPdf.pdf, target/surefire-reports/emailable-report.html", onlyIfSuccessful: false
                 publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/surefire-reports/', reportFiles: 'emailable-report.html', reportName: 'HTML Report', reportTitles: ''])
             }
         }
 }

