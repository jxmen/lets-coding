import http from 'k6/http';
import {check, group} from 'k6';

export default function() {
    group('index는 status 200을 응답한다', () => {
        const indexResponse = http.get('http://localhost:8080');
        check(indexResponse, {
            'status is 200': (r) => r.status === 200,
        });
    });

    group ('version api는 status200과 1.3.6을 응답한다', () => {
        const versionApiResponse = http.get('http://localhost:8080/api/version');
        check(versionApiResponse, {
            'status is 200': (r) => r.status === 200,
            'version is 1.3.6': (r) => JSON.parse(r.body).version === '1.3.6'
        });
    });
}
