import string
from azure.devops.connection import Connection
from msrest.authentication import BasicAuthentication
import argparse
import logging
import json

logger = logging.getLogger(__name__)

def _init_parser():
    parser = argparse.ArgumentParser("Create a PR status based on the diff coverage results.")
    parser.add_argument('token')
    parser.add_argument('organization_url')
    parser.add_argument('project_name')
    parser.add_argument('repository_name')
    parser.add_argument('pull_request_id')
    parser.add_argument('build_id')
    parser.add_argument('min_coverage')
    parser.add_argument('html_report_location')
    parser.add_argument('json_report_location')
    return parser

def create_status(organization_url: str, project_name: str, build_id: str):
    pr_status = {
        "state": "succeeded",
        "description": "Code coverage is ",
        "targetUrl": f"{organization_url}/{project_name}/_build/results?buildId={build_id}&view=artifacts&pathAsName=false&type=publishedArtifacts",
        "context": {
            "name": "coverage-checker",
            "genre": "continuous-integration"
        }
    }
    return pr_status

def update_status(pr_status, total_lines: str, coverage_percentage: str, min_coverage: str):
    pr_status["description"] += f"{coverage_percentage}%" if(int(total_lines) > 0) else "not applicable"
    if int(coverage_percentage) < int(min_coverage) and int(total_lines) > 0:
        pr_status["state"] = "failed"
    return pr_status

def main():
    parser = _init_parser()
    args = parser.parse_args()
    credentials = BasicAuthentication('', args.token)
    connection = Connection(base_url=args.organization_url, creds=credentials, user_agent='azure_devops_python_user_agent')
    status = create_status(args.organization_url, args.project_name, args.build_id)
    json_report = json.load(open(args.json_report_location))
    total_coverage = json_report["total_percent_covered"]
    total_num_lines = json_report["total_num_lines"]
    status = update_status(status, total_num_lines, total_coverage, args.min_coverage)
     #Get this exact version of the client as 'create_pull_request_status' is not in the release package yet.
    client = connection.get_client('azure.devops.v6_0.git.git_client.GitClient')
    client.create_pull_request_status(status, args.repository_name, args.pull_request_id, project=args.project_name)

if __name__ == "__main__":
    main()
