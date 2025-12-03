const API_BASE = "http://localhost:8080/api/mengenmeldungen";

async function loadMeldungen() {
    const tbody = document.querySelector("#mengenTable tbody");
    tbody.innerHTML = "<tr><td colspan='7'>Lade...</td></tr>";

    try {
        const resp = await fetch(API_BASE);
        if (!resp.ok) {
            throw new Error("Fehler beim Laden: " + resp.status);
        }
        const data = await resp.json();

        if (data.length === 0) {
            tbody.innerHTML = "<tr><td colspan='7'>Noch keine Meldungen vorhanden.</td></tr>";
            return;
        }

        tbody.innerHTML = "";
        for (const m of data) {
            const tr = document.createElement("tr");

            const statusClass = (m.status || "").toLowerCase(); // pending / sent / failed

            tr.innerHTML = `
                <td>${m.id}</td>
                <td>${m.herstellerId}</td>
                <td>${m.kategorie}</td>
                <td>${m.menge}</td>
                <td>${m.zeitraumVon} bis ${m.zeitraumBis}</td>
                <td class="status ${statusClass}">${m.status}</td>
                <td>${m.createdAt}</td>
            `;
            tbody.appendChild(tr);
        }
    } catch (e) {
        tbody.innerHTML = `<tr><td colspan='7' class='error'>${e.message}</td></tr>`;
        console.error(e);
    }
}

async function submitForm(event) {
    event.preventDefault();
    const msg = document.getElementById("formMessage");
    msg.textContent = "";
    msg.className = "";

    const herstellerId = document.getElementById("herstellerId").value;
    const kategorie = document.getElementById("kategorie").value;
    const menge = parseFloat(document.getElementById("menge").value);
    const zeitraumVon = document.getElementById("zeitraumVon").value;
    const zeitraumBis = document.getElementById("zeitraumBis").value;

    const payload = {
        herstellerId,
        kategorie,
        menge,
        zeitraumVon,
        zeitraumBis
    };

    try {
        const resp = await fetch(API_BASE, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!resp.ok) {
            const text = await resp.text();
            throw new Error("Fehler beim Senden: " + resp.status + " " + text);
        }

        const created = await resp.json();
        msg.textContent = `Mengenmeldung ${created.id} erfolgreich angelegt.`;
        msg.className = "success";

        // Tabelle aktualisieren
        await loadMeldungen();
    } catch (e) {
        msg.textContent = e.message;
        msg.className = "error";
        console.error(e);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("mengenForm")
        .addEventListener("submit", submitForm);

    document.getElementById("reloadBtn")
        .addEventListener("click", () => loadMeldungen());

    loadMeldungen();
});
