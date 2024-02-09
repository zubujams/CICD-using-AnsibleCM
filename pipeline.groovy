pipeline{
  agent any //here agent any means, any available serv
  stages{
    stage('Clone the repo')
    {
      steps{
        git branch:'main',url:'URL'
      }
    }
    stage('Install packages')
    {
      steps{
        ansiblePlaybook credentialsId: 'ansiblecredentials', disableHostKeyChecking: true, installation: 'myansible', inventory: 'dev.inv', playbook:'playbook1_Installs.yml', vaultTmpPath:"
      }
    }
    stage('Deploy the application')
    {
      steps{
        ansiblePlaybook credentialsId: 'ansiblecredentials', disableHostKeyChecking: true, installation: 'myansible', inventory: 'dev.inv', playbook:'playbook2_deploy.yml', vaultTmpPath:"
      }
    }
    
