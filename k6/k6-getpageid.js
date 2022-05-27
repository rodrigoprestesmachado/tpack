import { sleep } from 'k6';
import http from 'k6/http';

export const options = {
    vus: 10,
    duration: '60s',
    thresholds: {
        http_req_failed: ['rate<0.05'],
    },
};

export default function () {
    // Página aleatória entre 1 e 12
    let page = Math.floor(Math.random() * 12) + 1;
    http.get('http://localhost:8080/tpack/getPage/' + page);
    sleep(0.1);
}