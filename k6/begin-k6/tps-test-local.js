import http from "k6/http";
import {check, group} from "k6";

const LOCAL_BASE_SERVER_URL = "http://localhost:8080";
const baseServerUrl = LOCAL_BASE_SERVER_URL;

const token = "<YOUR_TOKEN>";

/**
 * define the load test options
 *
 * https://grafana.com/docs/k6/latest/using-k6/scenarios/
 */
export const options = {
    scenarios: {
        shared_iter_scenario: {
            executor: "shared-iterations",
            vus: 20,
            iterations: 500, // vus * n = iterations
            startTime: "0s",
            // duration: '10s', // 테스트 시간
        },
    },
};

export default function () {
    group("google login - /oauth2/authorization/google", () => {
        const url = `${baseServerUrl}/oauth2/authorization/google`;

        const res = http.get(url, {
            headers: {
                Referer: "https://cs-ai.jxmen.dev",
            }
        });
        check(res, {
            // 302 -> google로 리다이렉트되어 200이 된다.
            "status is 200": (r) => r.status === 200,
        });
    })

    group("subject/my api test", () => {
        const category = `dsa`;
        const url = `${baseServerUrl}/api/v1/subjects/my?category=${category}`;
        const params = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        const res = http.get(url, params);
        check(res, {
            "status is 200": (r) => r.status === 200,
            "response body success is true": (r) => JSON.parse(r.body).success === true
        });
    })

    group("detail and chat test", () => {
        const detailResponse = http.get(`${baseServerUrl}/api/v1/subjects/1`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        check(detailResponse, {
            "status is 200": (r) => r.status === 200,
            "response body success is true": (r) => JSON.parse(r.body).success === true
        });
        const chatResponse = http.get(`${baseServerUrl}/api/v1/subjects/1/chats`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        check(chatResponse, {
            "status is 200": (r) => r.status === 200,
            "response body success is true": (r) => JSON.parse(r.body).success === true
        });
    })

    // TODO: answer의 경우 AI 과금이 청구되므로, 비용을 고려하여 테스트를 진행해야 한다.
}
