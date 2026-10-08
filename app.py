
import subprocess
from pathlib import Path

import streamlit as st


PROJECT_DIR = Path(__file__).resolve().parent


st.set_page_config(
    page_title="Прогнозування бетонних робіт",
    page_icon="🏗️",
    layout="centered",
)


@st.cache_resource
def compile_java():
    subprocess.run(
        ["javac", "Predict.java"],
        cwd=PROJECT_DIR,
        capture_output=True,
        text=True,
        check=True,
        timeout=30,
    )


def predict_concrete_volume(temperature: float, productivity: str) -> float:
    compile_java()

    result = subprocess.run(
        [
            "java",
            "-cp",
            str(PROJECT_DIR),
            "Predict",
            str(temperature),
            productivity,
        ],
        cwd=PROJECT_DIR,
        capture_output=True,
        text=True,
        check=True,
        timeout=15,
    )

    output = result.stdout.strip()

    for line in output.splitlines():
        if "Prediction_0 =" in line:
            return float(line.split("=", 1)[1].strip())

    raise ValueError(f"Не вдалося отримати прогноз: {output}")


st.title("🏗️ Прогнозування обсягу бетонних робіт")

st.write(
    "Застосунок використовує штучну нейронну мережу, "
    "навчену в STATISTICA, для прогнозування обсягу "
    "виконаних бетонних робіт."
)

st.divider()

st.subheader("Вхідні параметри")

temperature = st.number_input(
    "Температура повітря (°C)",
    min_value=5.0,
    max_value=36.0,
    value=20.0,
    step=1.0,
)

productivity_options = {
    "Висока": "High",
    "Середня": "Medium",
    "Низька": "Low",
}

productivity_label = st.selectbox(
    "Рівень продуктивності",
    options=list(productivity_options.keys()),
    index=1,
)

st.divider()

if st.button(
    "Розрахувати прогноз",
    type="primary",
    use_container_width=True,
):
    try:
        prediction = predict_concrete_volume(
            temperature,
            productivity_options[productivity_label],
        )

        st.subheader("Результат прогнозування")

        st.metric(
            "Прогнозований обсяг бетону",
            f"{prediction:.2f} м³",
        )

        st.success("Прогноз успішно розраховано.")

        st.caption(
            "Результат отримано за допомогою "
            "нейронної мережі MLP 4-3-1."
        )

    except (subprocess.SubprocessError, ValueError, OSError) as error:
        st.error(f"Помилка прогнозування: {error}")
