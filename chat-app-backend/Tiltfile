LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = "practices-sandbox"

allow_k8s_contexts('iterate-admin@iterate-pinniped')

update_settings (k8s_upsert_timeout_secs = 300)

k8s_custom_deploy(
    'event-chat',
    apply_cmd="tanzu apps workload apply -f config/workload.yaml --update-strategy replace --debug --live-update" +
               " --local-path " + LOCAL_PATH +
               " --namespace " + NAMESPACE +
               " --yes --output yaml",
    delete_cmd="tanzu apps workload delete -f config/workload.yaml --namespace " + NAMESPACE + " --yes",
    container_selector='workload',


    deps=['./build/classes/java/main'],
    live_update=[
        sync('./build/classes/java/main', '/workspace/BOOT-INF/classes')
    ]
)

k8s_resource('event-chat', port_forwards=["8080:8080"],
            extra_pod_selectors=[{'carto.run/workload-name': 'event-chat', 'app.kubernetes.io/component': 'run'}])
allow_k8s_contexts('iterate-admin@iterate')