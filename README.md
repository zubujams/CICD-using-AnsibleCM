# CICD-using-AnsibleCM
CI/CD Deployment Using Ansible CM Tool

![Screenshot 2024-02-08 at 5 10 08 PM](https://github.com/zubujams/CICD-using-AnsibleCM/assets/52971863/46baa38b-072c-4a52-bb4e-ab9d5f3848b3)

#### Step 1: Configuring Jenkins as an Ansible Provisioning Machine
To use Jenkins as an Ansible provisioning machine, you first need to ensure that both Jenkins and Ansible are installed on the server. If Ansible is not yet installed, you can install it using your server's package manager (e.g., apt for Ubuntu, yum for CentOS).

#### Step 2: Install Ansible Plugins in Jenkins CI Server
Go to your Jenkins dashboard, navigate to Manage Jenkins > Manage Plugins.
In the Available tab, search for "Ansible" and install the following plugins:
Ansible Plugin: This allows Jenkins to invoke Ansible playbooks.
Pipeline Utility Steps: Useful for reading files within the Jenkins pipeline, which can be handy for Ansible inventory or variable files.

After installation, you might need to restart Jenkins for the changes to take effect.

#### Step 3: Ansible Playbooks Preparation
You'll prepare two main Ansible playbooks as described:

##### Playbook 1: Installation of Java, Maven, and Docker
This playbook targets the provisioning of essential tools required for the build and deployment processes. It updates the package lists, installs Maven and Docker, and ensures the Docker service is running. Here's an overview of the tasks:

Update the system's package list.
Install Java (ensure you add this step, as it's crucial for Maven).
Install Maven.
Install Docker.
Start the Docker service.

##### Playbook 2: Code Build and Deployment
This playbook handles cloning the repository, building the project with Maven, preparing the Docker image, and running the container:

Clone the project repository.
Execute the Maven build to generate the WAR file.
Copy the Dockerfile to each host.
Build the Docker image with the application.
Run the Docker container to deploy the application.

#### Step 4: Dockerfile and Deployment Artifacts
Ensure you have a Dockerfile prepared in your project's repository that correctly packages the WAR file into a Tomcat (or your chosen web container) Docker image. The Dockerfile should perform the following:

Start from a base Tomcat/Jetty image.
Copy the WAR file into the web container's deployment directory.
Configure any necessary environment variables or settings required for your application to run.

#### Step 5: Jenkins Pipeline Configuration
In Jenkins, you will set up a pipeline job that defines the stages of your CI/CD process, integrating the Ansible playbooks. Use the pipeline syntax provided in the PDF as a template, adjusting URLs and paths as necessary:

Clone the repo: This stage clones your project's repository into the Jenkins workspace.
Install packages: Executes Playbook 1 to install Java, Maven, and Docker on the target machine(s).
Deploy the application: Runs Playbook 2 to build your application with Maven, build the Docker image, and deploy the container.

#### Additional Considerations 
Ensure the Jenkins server and any target machines have SSH access configured for Ansible.
Store sensitive information (like repository URLs, credentials) securely in Jenkins credentials store.
Regularly review and update your playbooks and Jenkins pipeline to reflect changes in your deployment process or environment configurations.
