<template>
    <div class="landing-page">
        <div class="noise"></div>
        <div class="grid-lines"></div>
        <div class="orb orb-blue"></div>
        <div class="orb orb-violet"></div>

        <header class="landing-nav container-wide">
            <a class="brand" href="#top" aria-label="Signal home"><span class="brand-symbol">S</span><span>signal<span
                        class="brand-dot">.</span></span></a>
            <nav class="desktop-nav" aria-label="Main navigation">
                <a href="#platform">Platform</a><a href="#solutions">Solutions</a><a href="#resources">Resources</a><a
                    href="#pricing">Pricing</a>
            </nav>
            <div class="nav-actions"><a class="nav-login" href="/login">Log in</a><a
                    class="button button-small button-gradient" href="#get-started">Get started <span>↗</span></a></div>
        </header>

        <main id="top">
            <section class="hero container-wide">
                <div class="hero-copy reveal">
                    <div class="eyebrow"><span class="pulse"></span> Incident operations workspace</div>
                    <h1>See every <span class="gradient-text">incident.</span><br />Act with clarity.</h1>
                    <p class="hero-text">Signal brings incident tracking, alert rules, infrastructure metrics, and
                        Grafana dashboards into one focused workspace.</p>
                    <div class="hero-actions"><a class="button button-gradient" href="/login">Open workspace
                            <span>↗</span></a><span class="form-note">Sign in to view your operations workspace</span>
                    </div>
                </div>
                <div class="hero-visual reveal reveal-delay" aria-label="Signal incident dashboard preview">
                    <div class="visual-glow"></div>
                    <div class="dashboard-window glass-card">
                        <div class="window-bar">
                            <div class="window-dots"><i></i><i></i><i></i></div><span>signal /
                                workspace</span><b>•••</b>
                        </div>
                        <div class="window-body">
                            <aside class="mini-sidebar">
                                <div class="mini-logo">S</div><span
                                    class="mini-active"></span><span></span><span></span><span></span>
                                <div class="mini-bottom"></div>
                            </aside>
                            <div class="mini-content">
                                <div class="mini-heading">
                                    <div><small>Tuesday, September 21</small>
                                        <h3>Good morning, Alex</h3>
                                    </div><span class="mini-avatar">AK</span>
                                </div>
                                <div class="mini-stats">
                                    <div><small>Open incidents</small><strong>07</strong><em class="up">↓ 18%</em></div>
                                    <div><small>MTTR</small><strong>24m</strong><em class="down">↓ 32%</em></div>
                                    <div><small>System health</small><strong>99.98%</strong><em class="healthy">●
                                            Healthy</em></div>
                                </div>
                                <div class="mini-panel">
                                    <div class="panel-label"><span>Incident volume</span><small>Last 7 days
                                            <b>⌄</b></small></div>
                                    <div class="chart">
                                        <div class="chart-fill"></div>
                                        <div class="chart-line"></div><span class="chart-tooltip">Today · 18</span><i
                                            class="chart-point"></i>
                                    </div>
                                    <div class="chart-labels">
                                        <span>MON</span><span>TUE</span><span>WED</span><span>THU</span><span>FRI</span><span>SAT</span><span>SUN</span>
                                    </div>
                                </div>
                                <div class="mini-panel incidents-panel">
                                    <div class="panel-label"><span>Active incidents</span><small class="view-link">View
                                            all →</small></div>
                                    <div class="incident-row"><i class="severity critical"></i><span><b>API latency
                                                spike</b><small>Payments service · 8 min
                                                ago</small></span><em>Investigating</em></div>
                                    <div class="incident-row"><i class="severity warning"></i><span><b>Checkout
                                                errors</b><small>Web app · 23 min ago</small></span><em
                                            class="muted">Monitoring</em></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="logo-cloud container-wide reveal" aria-label="Workspace capabilities"><span>ONE WORKSPACE
                    FOR</span>
                <div><b>Incidents</b><b>Alerts</b><b>Monitoring</b><b>Grafana</b><b>Settings</b></div>
            </section>

            <section id="platform" class="section container-wide">
                <div class="section-heading reveal"><span class="section-kicker">THE WORKSPACE</span>
                    <h2>Everything your team needs<br /><span class="muted-gradient">to operate with context.</span>
                    </h2>
                    <p>Keep the incident lifecycle, alert rules, and infrastructure health visible in one place.</p>
                </div>
                <div class="bento-grid" id="solutions">
                    <article class="bento-card bento-large glass-card reveal">
                        <div class="card-copy"><span class="card-number">01</span>
                            <h3>Track every incident.</h3>
                            <p>Create, search, filter, edit, resolve, and delete incidents with status and severity
                                always in view.</p><a href="/login">Open incident management <span>↗</span></a>
                        </div>
                        <div class="radar-visual">
                            <div class="radar-ring ring-one"></div>
                            <div class="radar-ring ring-two"></div>
                            <div class="radar-ring ring-three"></div>
                            <div class="radar-sweep"></div><span class="radar-node node-one"></span><span
                                class="radar-node node-two"></span><span class="radar-node node-three"></span>
                        </div>
                    </article>
                    <article class="bento-card bento-tall glass-card reveal reveal-delay"><span
                            class="card-number">02</span>
                        <h3>Follow the<br />incident lifecycle.</h3>
                        <p>Keep the journey from open alert to resolved incident clear for everyone on call.</p>
                        <div class="timeline"><span><i></i><b>Incident created</b><small>Open · P1
                                    High</small></span><span class="active"><i></i><b>Response in
                                    progress</b><small>Assigned to team</small></span><span><i></i><b>Incident
                                    resolved</b><small>Closed with context</small></span></div>
                    </article>
                    <article class="bento-card bento-medium glass-card reveal"><span class="card-number">03</span>
                        <h3>Configure clear<br /><span>alert rules.</span></h3>
                        <p>Set metrics, operators, thresholds, severity, channels, and enabled status from one table.
                        </p>
                        <div class="automation-pill"><span class="spark">⌁</span><span>Alert rule</span><b>Enabled</b>
                        </div>
                    </article>
                    <article class="bento-card bento-medium glass-card reveal reveal-delay"><span
                            class="card-number">04</span>
                        <h3>Monitor your<br />infrastructure.</h3>
                        <p>Review CPU, memory, disk, network I/O, Prometheus metrics, and Grafana panels.</p>
                        <div class="progress-lines"><span><i style="width: 82%"></i></span><span><i
                                    style="width: 64%"></i></span><span><i style="width: 91%"></i></span></div>
                    </article>
                </div>
            </section>

            <section class="metrics-section section container-wide reveal">
                <div class="metric-intro"><span class="section-kicker">A SIMPLE WORKFLOW</span>
                    <h2>From signal to<br /><span class="gradient-text">next action.</span></h2>
                </div>
                <div class="metrics-grid">
                    <div><strong>01</strong><span>Review the dashboard<br />and active incidents</span></div>
                    <div><strong>02</strong><span>Inspect alert rules<br />and thresholds</span></div>
                    <div><strong>03</strong><span>Refresh infrastructure<br />and Grafana metrics</span></div>
                </div>
            </section>

            <section id="resources" class="testimonial-section section">
                <div class="container-narrow"><span class="section-kicker">CONFIGURE YOUR WORKSPACE</span>
                    <blockquote>“Everything important is close at hand: incidents to manage, rules to tune,
                        infrastructure to inspect, and integrations to configure.”</blockquote>
                    <div class="quote-author"><span class="author-avatar">S</span><span><b>Signal
                                workspace</b><small>User, API, notification, and Grafana settings</small></span></div>
                </div>
            </section>

            <section id="get-started" class="final-cta container-wide reveal">
                <div class="cta-orbit orbit-a"></div>
                <div class="cta-orbit orbit-b"></div><span class="section-kicker">READY WHEN YOU ARE</span>
                <h2>Bring your<br /><span class="gradient-text">workspace together.</span></h2>
                <p>Sign in to manage incidents, alerts, monitoring, and settings.</p><a class="button button-gradient"
                    href="/login">Open workspace <span>↗</span></a>
            </section>
        </main>

        <footer class="landing-footer container-wide"><a class="brand" href="#top"><span
                    class="brand-symbol">S</span><span>signal<span class="brand-dot">.</span></span></a><span>© 2026
                Signal, Inc.</span>
            <div><a href="#platform">Platform</a><a href="#resources">Company</a><a href="/login">Login</a></div>
        </footer>
    </div>
</template>

<script setup>
import { onMounted } from 'vue'

onMounted(() => {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) entry.target.classList.add('is-visible')
        })
    }, { threshold: 0.12 })
    document.querySelectorAll('.reveal').forEach((element) => observer.observe(element))
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=DM+Mono:wght@400;500&family=Manrope:wght@400;500;600;700;800&display=swap');

:global(body) {
    background: #0a0a0b;
}

:global(#app) {
    min-height: 100vh;
}

.landing-page {
    --blue: #5b8cff;
    --violet: #a855f7;
    --cyan: #22d3ee;
    --ink: #0a0a0b;
    --white: #f4f5f7;
    --soft: #9b9ca5;
    position: relative;
    min-height: 100vh;
    overflow: hidden;
    color: var(--white);
    background: #0a0a0b;
    font-family: 'Manrope', sans-serif;
}

.noise {
    position: fixed;
    inset: 0;
    z-index: 0;
    opacity: .035;
    pointer-events: none;
    background-image: url("data:image/svg+xml,%3Csvg viewBox='0 0 180 180' xmlns='http://www.w3.org/2000/svg'%3E%3Cfilter id='n'%3E%3CfeTurbulence type='fractalNoise' baseFrequency='.85' numOctaves='4' stitchTiles='stitch'/%3E%3C/filter%3E%3Crect width='100%25' height='100%25' filter='url(%23n)' opacity='.5'/%3E%3C/svg%3E");
}

.grid-lines {
    position: absolute;
    inset: 0;
    z-index: 0;
    pointer-events: none;
    opacity: .3;
    background-image: linear-gradient(rgba(255, 255, 255, .035) 1px, transparent 1px), linear-gradient(90deg, rgba(255, 255, 255, .035) 1px, transparent 1px);
    background-size: 80px 80px;
    mask-image: linear-gradient(to bottom, black, transparent 72%);
}

.orb {
    position: absolute;
    z-index: 0;
    border-radius: 50%;
    filter: blur(90px);
    pointer-events: none;
    opacity: .23;
    animation: breathe 8s ease-in-out infinite alternate;
}

.orb-blue {
    width: 460px;
    height: 460px;
    top: 50px;
    left: 6%;
    background: #315ee9;
}

.orb-violet {
    width: 380px;
    height: 380px;
    top: 380px;
    right: 8%;
    background: #6d2ca8;
    animation-delay: -3s;
}

.container-wide {
    position: relative;
    z-index: 1;
    width: min(1200px, calc(100% - 64px));
    margin: 0 auto;
}

.container-narrow {
    width: min(780px, calc(100% - 64px));
    margin: 0 auto;
}

.landing-nav {
    height: 88px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid rgba(255, 255, 255, .08);
}

.brand {
    display: inline-flex;
    align-items: center;
    gap: 10px;
    color: var(--white);
    font-size: 17px;
    font-weight: 800;
    letter-spacing: -.06em;
    text-decoration: none;
}

.brand-symbol {
    display: grid;
    place-items: center;
    width: 28px;
    height: 28px;
    border: 1px solid rgba(255, 255, 255, .3);
    border-radius: 8px;
    background: linear-gradient(145deg, rgba(91, 140, 255, .9), rgba(168, 85, 247, .75));
    font-size: 13px;
    letter-spacing: 0;
}

.brand-dot {
    color: var(--cyan);
}

.desktop-nav {
    display: flex;
    gap: 32px;
    margin-left: 80px;
}

.desktop-nav a,
.nav-login,
.landing-footer a {
    color: #a4a5ae;
    font-size: 12px;
    text-decoration: none;
    transition: color .2s;
}

.desktop-nav a:hover,
.nav-login:hover,
.landing-footer a:hover {
    color: #fff;
}

.nav-actions {
    display: flex;
    align-items: center;
    gap: 24px;
}

.button {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    gap: 16px;
    min-height: 48px;
    padding: 0 21px;
    border: 0;
    border-radius: 10px;
    color: white;
    font: 700 12px 'Manrope', sans-serif;
    text-decoration: none;
    cursor: pointer;
    transition: transform .25s, box-shadow .25s;
}

.button:hover {
    transform: translateY(-2px);
    box-shadow: 0 10px 30px rgba(91, 140, 255, .28);
}

.button span {
    font-size: 15px;
}

.button-small {
    min-height: 38px;
    padding: 0 16px;
    border-radius: 8px;
}

.button-gradient {
    background: linear-gradient(105deg, var(--blue), var(--violet) 56%, var(--cyan));
    background-size: 180% 100%;
    animation: gradientMove 7s ease infinite;
}

.hero {
    display: grid;
    grid-template-columns: 5fr 7fr;
    gap: 30px;
    align-items: center;
    min-height: 690px;
    padding: 92px 0 105px;
}

.hero-copy {
    padding-left: 4px;
}

.eyebrow,
.section-kicker {
    color: #91939f;
    font: 500 10px 'DM Mono', monospace;
    letter-spacing: .16em;
    text-transform: uppercase;
}

.eyebrow {
    display: flex;
    align-items: center;
    gap: 9px;
    color: #a6a9b5;
}

.pulse {
    width: 7px;
    height: 7px;
    border-radius: 50%;
    background: var(--cyan);
    box-shadow: 0 0 14px var(--cyan);
}

.hero h1 {
    margin: 25px 0 23px;
    color: #f8f8fb;
    font-size: clamp(52px, 6vw, 83px);
    line-height: .99;
    letter-spacing: -.075em;
    font-weight: 700;
}

.gradient-text {
    background: linear-gradient(105deg, var(--blue), var(--violet) 53%, var(--cyan));
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
    background-size: 180% 100%;
    animation: gradientMove 7s ease infinite;
}

.hero-text {
    max-width: 445px;
    color: #999ba6;
    font-size: 15px;
    line-height: 1.75;
}

.signup-form {
    display: flex;
    gap: 8px;
    max-width: 425px;
    margin-top: 35px;
    padding: 5px;
    border: 1px solid rgba(255, 255, 255, .16);
    border-radius: 12px;
    background: rgba(255, 255, 255, .045);
}

.signup-form input {
    min-width: 0;
    flex: 1;
    padding: 0 12px;
    border: 0;
    outline: 0;
    color: #fff;
    background: transparent;
    font: 12px 'Manrope', sans-serif;
}

.signup-form input::placeholder {
    color: #7f818b;
}

.form-note {
    margin: 12px 0 0 13px;
    color: #6f717d;
    font-size: 10px;
}

.form-note.success {
    color: var(--cyan);
}

.hero-visual {
    position: relative;
    min-width: 0;
}

.visual-glow {
    position: absolute;
    inset: 14% -4% 4% 5%;
    background: linear-gradient(120deg, #3c70ea, #9b3af0);
    opacity: .2;
    filter: blur(70px);
}

.glass-card {
    border: 1px solid rgba(255, 255, 255, .13);
    background: linear-gradient(135deg, rgba(255, 255, 255, .095), rgba(255, 255, 255, .035));
    box-shadow: 0 30px 80px rgba(0, 0, 0, .36), inset 0 1px rgba(255, 255, 255, .09);
    backdrop-filter: blur(18px);
}

.dashboard-window {
    position: relative;
    overflow: hidden;
    border-radius: 17px;
    transform: perspective(1200px) rotateY(-4deg) rotateX(2deg);
    transition: transform .5s;
}

.dashboard-window:hover {
    transform: perspective(1200px) rotateY(0) rotateX(0) translateY(-5px);
}

.window-bar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 40px;
    padding: 0 15px;
    border-bottom: 1px solid rgba(255, 255, 255, .08);
    color: #656773;
    font: 8px 'DM Mono', monospace;
}

.window-dots {
    display: flex;
    gap: 5px;
}

.window-dots i {
    width: 6px;
    height: 6px;
    border-radius: 50%;
    background: #51535c;
}

.window-dots i:first-child {
    background: #ee738d;
}

.window-dots i:last-child {
    background: #74c8a1;
}

.window-body {
    display: flex;
    min-height: 382px;
    background: rgba(8, 9, 13, .65);
}

.mini-sidebar {
    display: flex;
    flex: 0 0 43px;
    flex-direction: column;
    align-items: center;
    gap: 21px;
    padding-top: 18px;
    border-right: 1px solid rgba(255, 255, 255, .07);
}

.mini-logo {
    display: grid;
    place-items: center;
    width: 22px;
    height: 22px;
    border-radius: 6px;
    color: #fff;
    background: linear-gradient(140deg, var(--blue), var(--violet));
    font-size: 10px;
    font-weight: 800;
}

.mini-sidebar span:not(.mini-active) {
    width: 11px;
    height: 11px;
    border: 1px solid #5b5f6a;
    border-radius: 3px;
}

.mini-sidebar .mini-active {
    width: 19px;
    height: 19px;
    border-radius: 5px;
    background: rgba(91, 140, 255, .3);
    box-shadow: 0 0 12px #416de8;
}

.mini-bottom {
    margin-top: auto;
    width: 16px;
    height: 16px;
    margin-bottom: 20px;
    border: 1px solid #5b5f6a;
    border-radius: 50%;
}

.mini-content {
    flex: 1;
    padding: 24px 28px;
}

.mini-heading {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
}

.mini-heading small,
.mini-stats small,
.panel-label,
.chart-labels,
.incident-row small {
    color: #737681;
    font-size: 8px;
}

.mini-heading h3 {
    margin: 6px 0 0;
    color: #ececf1;
    font-size: 16px;
    letter-spacing: -.04em;
}

.mini-avatar,
.author-avatar {
    display: grid;
    place-items: center;
    border-radius: 50%;
    color: #bbcaff;
    background: #26355f;
    font-size: 8px;
}

.mini-avatar {
    width: 24px;
    height: 24px;
}

.mini-stats {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 7px;
    margin: 23px 0 12px;
}

.mini-stats>div,
.mini-panel {
    padding: 13px;
    border: 1px solid rgba(255, 255, 255, .08);
    border-radius: 8px;
    background: rgba(255, 255, 255, .035);
}

.mini-stats strong {
    display: block;
    margin: 3px 0 2px;
    color: #f0f1f4;
    font-size: 19px;
    letter-spacing: -.06em;
}

.mini-stats em {
    font-size: 7px;
    font-style: normal;
}

.up {
    color: #6dd5aa;
}

.down {
    color: #73a3ff;
}

.healthy {
    color: #6dd5aa;
}

.mini-panel {
    margin-top: 11px;
}

.panel-label {
    display: flex;
    justify-content: space-between;
    color: #acadb5;
    font-size: 9px;
}

.panel-label small {
    font-size: 8px;
}

.panel-label b {
    color: #c9cad0;
}

.chart {
    position: relative;
    height: 75px;
    margin-top: 13px;
    overflow: hidden;
    background: repeating-linear-gradient(to bottom, transparent 0, transparent 24px, rgba(255, 255, 255, .04) 25px);
}

.chart-fill {
    position: absolute;
    inset: 21px 0 0;
    background: linear-gradient(to bottom, rgba(91, 140, 255, .22), transparent);
    clip-path: polygon(0 72%, 8% 63%, 18% 77%, 28% 44%, 40% 60%, 51% 31%, 63% 55%, 73% 25%, 84% 38%, 94% 8%, 100% 15%, 100% 100%, 0 100%);
}

.chart-line {
    position: absolute;
    inset: 0;
    background: linear-gradient(155deg, transparent 29%, #6f9cff 30%, #6f9cff 31%, transparent 32%) 0 30px/16% 27px repeat-x;
    opacity: .8;
}

.chart-tooltip {
    position: absolute;
    top: 2px;
    right: 11%;
    padding: 4px 6px;
    border: 1px solid rgba(110, 158, 255, .4);
    border-radius: 4px;
    color: #a9c4ff;
    background: #202d50;
    font-size: 7px;
}

.chart-point {
    position: absolute;
    top: 30px;
    right: 10%;
    width: 5px;
    height: 5px;
    border: 1px solid white;
    border-radius: 50%;
    background: var(--blue);
    box-shadow: 0 0 8px var(--blue);
}

.chart-labels {
    display: flex;
    justify-content: space-between;
    margin-top: 6px;
    font: 6px 'DM Mono', monospace;
}

.incidents-panel {
    padding-bottom: 4px;
}

.view-link {
    color: #7b9fff !important;
}

.incident-row {
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 12px 0 7px;
    border-bottom: 1px solid rgba(255, 255, 255, .06);
}

.incident-row>span {
    flex: 1;
    min-width: 0;
}

.incident-row b,
.incident-row small {
    display: block;
}

.incident-row b {
    color: #d4d5da;
    font-size: 8px;
}

.incident-row small {
    margin-top: 2px;
    font-size: 7px;
}

.incident-row em {
    color: #e9a2b0;
    font-size: 7px;
    font-style: normal;
}

.incident-row em.muted {
    color: #7ba4ff;
}

.severity {
    width: 6px;
    height: 6px;
    border-radius: 50%;
}

.critical {
    background: #f0708d;
    box-shadow: 0 0 7px #f0708d;
}

.warning {
    background: #edbd70;
    box-shadow: 0 0 7px #edbd70;
}

.logo-cloud {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 25px 0 105px;
    border-top: 1px solid rgba(255, 255, 255, .08);
}

.logo-cloud>span {
    color: #646671;
    font: 9px 'DM Mono', monospace;
    letter-spacing: .14em;
}

.logo-cloud>div {
    display: flex;
    align-items: center;
    gap: clamp(22px, 5vw, 70px);
}

.logo-cloud b {
    color: #656771;
    font-size: 17px;
    letter-spacing: -.08em;
}

.logo-cloud b:nth-child(2) {
    font-size: 22px;
}

.logo-cloud b:nth-child(3) {
    font-weight: 500;
}

.logo-cloud b:nth-child(4) {
    font-size: 20px;
}

.logo-cloud span {
    color: #777a86;
}

.logo-x {
    color: var(--blue) !important;
}

.section {
    padding: 40px 0 115px;
}

.section-heading {
    margin-bottom: 43px;
}

.section-heading h2,
.metric-intro h2,
.final-cta h2 {
    margin: 19px 0 14px;
    color: #f5f5f8;
    font-size: clamp(35px, 4.3vw, 56px);
    line-height: 1.05;
    letter-spacing: -.07em;
}

.section-heading p,
.final-cta p {
    color: #8f919c;
    font-size: 14px;
}

.muted-gradient {
    color: #686b77;
}

.bento-grid {
    display: grid;
    grid-template-columns: repeat(12, 1fr);
    grid-template-rows: 310px 260px;
    gap: 13px;
}

.bento-card {
    position: relative;
    overflow: hidden;
    padding: 29px;
    border-radius: 18px;
}

.bento-large {
    grid-column: span 7;
}

.bento-tall {
    grid-column: span 5;
    grid-row: span 2;
}

.bento-medium {
    grid-column: span 7;
}

.bento-card .card-number {
    color: #676a76;
    font: 9px 'DM Mono', monospace;
}

.bento-card h3 {
    margin: 21px 0 11px;
    color: #f1f1f4;
    font-size: 21px;
    letter-spacing: -.055em;
}

.bento-card p {
    max-width: 310px;
    color: #858792;
    font-size: 12px;
    line-height: 1.7;
}

.bento-card a {
    display: inline-block;
    margin-top: 22px;
    color: #9ab8ff;
    font-size: 11px;
    text-decoration: none;
}

.bento-card a span {
    margin-left: 7px;
}

.bento-large .card-copy {
    position: relative;
    z-index: 2;
}

.radar-visual {
    position: absolute;
    right: -5%;
    bottom: -26%;
    width: 350px;
    height: 350px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(91, 140, 255, .15), transparent 62%);
}

.radar-ring {
    position: absolute;
    inset: 20%;
    border: 1px solid rgba(91, 140, 255, .26);
    border-radius: 50%;
}

.ring-two {
    inset: 33%;
    border-color: rgba(168, 85, 247, .32);
}

.ring-three {
    inset: 45%;
    border-color: rgba(34, 211, 238, .4);
}

.radar-sweep {
    position: absolute;
    inset: 8%;
    border-radius: 50%;
    background: conic-gradient(from 200deg, transparent 0 73%, rgba(91, 140, 255, .2) 80%, transparent 87%);
}

.radar-node {
    position: absolute;
    width: 6px;
    height: 6px;
    border-radius: 50%;
    background: var(--cyan);
    box-shadow: 0 0 12px var(--cyan);
}

.node-one {
    top: 32%;
    left: 41%;
}

.node-two {
    top: 63%;
    left: 30%;
    background: var(--violet);
    box-shadow: 0 0 12px var(--violet);
}

.node-three {
    top: 45%;
    right: 26%;
}

.bento-tall h3 {
    font-size: 29px;
    line-height: 1.02;
}

.bento-tall p {
    max-width: 250px;
}

.timeline {
    position: absolute;
    right: 29px;
    bottom: 27px;
    left: 29px;
    padding-left: 20px;
    border-left: 1px solid #31333f;
}

.timeline span {
    position: relative;
    display: block;
    margin-top: 18px;
}

.timeline span:first-child {
    margin-top: 0;
}

.timeline i {
    position: absolute;
    top: 3px;
    left: -24px;
    width: 7px;
    height: 7px;
    border: 1px solid #737583;
    border-radius: 50%;
    background: #16171d;
}

.timeline .active i {
    border-color: var(--blue);
    background: var(--blue);
    box-shadow: 0 0 12px var(--blue);
}

.timeline b,
.timeline small {
    display: block;
}

.timeline b {
    color: #b9bac1;
    font-size: 10px;
    font-weight: 500;
}

.timeline small {
    margin-top: 3px;
    color: #686a75;
    font: 8px 'DM Mono', monospace;
}

.bento-medium {
    display: flex;
    flex-direction: column;
}

.bento-medium h3 {
    margin-bottom: 7px;
}

.bento-medium h3 span {
    color: #777984;
}

.automation-pill {
    display: flex;
    align-items: center;
    gap: 8px;
    width: max-content;
    margin-top: auto;
    padding: 8px 10px;
    border: 1px solid rgba(168, 85, 247, .35);
    border-radius: 8px;
    color: #b5a4f3;
    background: rgba(168, 85, 247, .1);
    font-size: 9px;
}

.automation-pill b {
    margin-left: 18px;
    color: #68d8b0;
    font-size: 8px;
    font-weight: 500;
}

.spark {
    color: #ca93ff;
}

.progress-lines {
    display: flex;
    flex-direction: column;
    gap: 7px;
    margin-top: auto;
    width: 65%;
}

.progress-lines span {
    height: 4px;
    overflow: hidden;
    border-radius: 4px;
    background: rgba(255, 255, 255, .07);
}

.progress-lines i {
    display: block;
    height: 100%;
    border-radius: 4px;
    background: linear-gradient(90deg, var(--blue), var(--violet));
}

.metrics-section {
    display: grid;
    grid-template-columns: 3fr 9fr;
    align-items: end;
    gap: 70px;
    padding-top: 30px;
    border-top: 1px solid rgba(255, 255, 255, .08);
}

.metric-intro h2 {
    margin-bottom: 0;
    font-size: 40px;
}

.metrics-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 12px;
}

.metrics-grid>div {
    padding: 20px 22px;
    border-left: 1px solid rgba(255, 255, 255, .12);
}

.metrics-grid strong {
    display: block;
    color: #f7f7f9;
    font-size: 37px;
    letter-spacing: -.07em;
}

.metrics-grid span {
    display: block;
    margin-top: 8px;
    color: #7f818c;
    font-size: 11px;
    line-height: 1.5;
}

.testimonial-section {
    padding: 90px 0 115px;
    background: linear-gradient(180deg, transparent, rgba(74, 55, 145, .09), transparent);
}

.testimonial-section blockquote {
    margin: 28px 0 31px;
    color: #f0f0f4;
    font-size: clamp(24px, 3.1vw, 38px);
    line-height: 1.28;
    letter-spacing: -.055em;
}

.quote-author {
    display: flex;
    align-items: center;
    gap: 11px;
}

.author-avatar {
    width: 31px;
    height: 31px;
    background: linear-gradient(135deg, #26355f, #472b64);
    color: #d4c5ff;
}

.quote-author b,
.quote-author small {
    display: block;
}

.quote-author b {
    color: #d5d6dd;
    font-size: 11px;
}

.quote-author small {
    margin-top: 3px;
    color: #737580;
    font-size: 9px;
}

.final-cta {
    overflow: hidden;
    padding: 93px 0 109px;
    text-align: center;
    border: 1px solid rgba(255, 255, 255, .1);
    border-radius: 20px;
    background: radial-gradient(circle at 50% 120%, rgba(91, 140, 255, .23), transparent 55%), rgba(255, 255, 255, .025);
}

.final-cta h2 {
    position: relative;
    z-index: 1;
    margin-top: 18px;
    font-size: clamp(42px, 5.6vw, 72px);
}

.final-cta p {
    position: relative;
    z-index: 1;
    margin-bottom: 28px;
}

.cta-orbit {
    position: absolute;
    width: 460px;
    height: 460px;
    top: 38%;
    left: 50%;
    border: 1px solid rgba(91, 140, 255, .18);
    border-radius: 50%;
    transform: translate(-50%, -50%) rotate(-17deg);
}

.orbit-b {
    width: 600px;
    height: 200px;
    border-color: rgba(168, 85, 247, .18);
    transform: translate(-50%, -50%) rotate(28deg);
}

.landing-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 47px 0;
    color: #656772;
    font-size: 10px;
}

.landing-footer>div {
    display: flex;
    gap: 25px;
}

.landing-footer .brand {
    font-size: 14px;
}

.landing-footer .brand-symbol {
    width: 24px;
    height: 24px;
    font-size: 11px;
}

.reveal {
    opacity: 0;
    transform: translateY(18px);
    transition: opacity .7s ease, transform .7s ease;
}

.reveal.is-visible {
    opacity: 1;
    transform: translateY(0);
}

.reveal-delay {
    transition-delay: .13s;
}

.sr-only {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    white-space: nowrap;
    border: 0;
}

@keyframes breathe {
    to {
        transform: scale(1.1) translate(20px, 10px);
        opacity: .3;
    }
}

@keyframes gradientMove {

    0%,
    100% {
        background-position: 0 50%;
    }

    50% {
        background-position: 100% 50%;
    }
}

@media (max-width: 900px) {
    .hero {
        grid-template-columns: 1fr;
        padding-top: 70px;
    }

    .hero-copy {
        max-width: 620px;
        margin: 0 auto;
        text-align: center;
    }

    .eyebrow,
    .signup-form {
        margin-right: auto;
        margin-left: auto;
    }

    .hero-text {
        margin-right: auto;
        margin-left: auto;
    }

    .form-note {
        margin-left: 0;
    }

    .hero-visual {
        max-width: 700px;
        width: 100%;
        margin: 20px auto 0;
    }

    .desktop-nav {
        margin-left: 0;
    }

    .bento-large {
        grid-column: span 12;
    }

    .bento-tall {
        grid-column: span 12;
        grid-row: auto;
        min-height: 330px;
    }

    .bento-medium {
        grid-column: span 6;
    }

    .metrics-section {
        grid-template-columns: 1fr;
        gap: 30px;
    }
}

@media (max-width: 620px) {

    .container-wide,
    .container-narrow {
        width: min(100% - 36px, 1200px);
    }

    .landing-nav {
        height: 72px;
    }

    .desktop-nav,
    .nav-login {
        display: none;
    }

    .nav-actions {
        gap: 0;
    }

    .hero {
        min-height: 0;
        padding: 76px 0 80px;
    }

    .hero h1 {
        font-size: clamp(44px, 14vw, 64px);
    }

    .hero-text {
        font-size: 13px;
    }

    .signup-form {
        flex-direction: column;
        padding: 7px;
    }

    .signup-form input {
        min-height: 40px;
        text-align: center;
    }

    .logo-cloud {
        display: block;
        padding-bottom: 78px;
    }

    .logo-cloud>div {
        flex-wrap: wrap;
        justify-content: center;
        gap: 19px 30px;
        margin-top: 22px;
    }

    .bento-grid {
        display: flex;
        flex-direction: column;
    }

    .bento-card {
        min-height: 280px;
    }

    .bento-tall {
        min-height: 370px;
    }

    .radar-visual {
        right: -14%;
        bottom: -22%;
        transform: scale(.78);
        transform-origin: bottom right;
    }

    .metrics-grid {
        grid-template-columns: 1fr;
    }

    .metrics-grid>div {
        padding: 16px 0;
        border-left: 0;
        border-bottom: 1px solid rgba(255, 255, 255, .12);
    }

    .metric-intro h2 {
        font-size: 37px;
    }

    .testimonial-section {
        padding: 68px 0 82px;
    }

    .testimonial-section blockquote {
        font-size: 26px;
    }

    .final-cta {
        padding: 70px 20px 82px;
    }

    .landing-footer {
        align-items: flex-start;
        flex-direction: column;
        gap: 25px;
    }

    .landing-footer>div {
        gap: 16px;
    }
}
</style>
