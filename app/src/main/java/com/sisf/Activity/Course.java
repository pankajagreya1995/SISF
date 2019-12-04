package com.sisf.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sisf.R;

public class Course extends AppCompatActivity {

    TextView tv_des;
    ImageView background_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        int get_course_type=getIntent().getIntExtra("Select_course_type",0);

        tv_des=findViewById(R.id.txt_description);

        background_img=findViewById(R.id.img);

        Set_info(get_course_type);

    }

    private void Set_info(int get_course_type) {
        if (get_course_type==0)
        {
            background_img.setImageDrawable(getResources().getDrawable(R.drawable.img_acc));
            getSupportActionBar().setTitle("ACCA");
            tv_des.setText("Founded in 1904, the Association of Chartered Certified Accountants (ACCA) is the oldest global professional accounting body offering the Chartered Certified Accountant / ACCA qualification. With over 200,000 members and 486,000 students ACCA is the largest accounting body globally. ACCA being one of the most important accounting qualification, offers various benefits. Some of these advantages are as follows:\n\n" +
                    "· Global acceptance.\n" +
                    "ACCA is recognized globally, and can help you work at various financial roles in the country of your choice.\n\n" +
                    "· Industrial experience\n" +
                    "ACCA not only provides you a qualification but also opportunities to gain relevant experience and get industry ready alongside your qualification.\n\n" +
                    "· Employer’s demand\n" +
                    "Employers are very well known about the skills, value and contribution an ACCA qualified professional can make to the organization.\n\n" +
                    "· Industry relevant skills.\n" +
                    "The ACCA Qualification provides subtle relevancy to all forms of industries, which enables you to make a choice for your career after qualification as well.\n\n" +
                    "· Flexibility & innovative studying patterns.\n" +
                    "Innovative and flexible study patterns help you complete your ACCA Qualification alongside your busy work schedule or essential qualification.\n\n" +
                    "· Multiple degrees\n" +
                    "You are eligible to pursue additional diplomas and qualifications as you move forward with ACCA membership.\n\n" +
                    "· Employer network\n" +
                    "Along with a global recognized qualification ACCA also supports it’s members with extensive network of 7900 employers globally, in order to provide more job prospects on ACCA career portal.\n\n" +
                    "· CBE – computer based examination\n" +
                    "ACCA is supported with examination taken at the registered CBE centers to provide their students the ease of completing their papers on fast track basis.");
        }else if (get_course_type==1)
        {
            background_img.setImageDrawable(getResources().getDrawable(R.drawable.img_accapro));
            getSupportActionBar().setTitle("ACCA PRO");
            tv_des.setText("ACCAPRO is a unique combination of advanced financial and accounting qualification delivered only at SISF. A programme developed by intense research of the industry and it’s leaders, the curriculum benefits our students to procure a distinguished set of skills that will help them meet & exceed expectations in all future endeavors.\n" +
                    "The Programme is designed to deliver the core benefits of ACCA with a varied skillset to ensure immediate acceptance of our students in the industry. In ACCAPRO we provide ACCA qualification alongside with certifications in Business Analysis (ECBA by IIBA) as well as a CDTP (Corporate Development Training Programme) developed with the assistance of industry experts decorated with years of experience in the field of Recruitment and Human Resource Management. This unique blend of qualifications helps give an edge to our students against the competition and exhilarates their career growth in the field of Finance.\n\n" +
                    "ACCA:\n" +
                    "ACCA Qualification is renowned globally in order to pursue careers in high standards of accounting in various fields.\n\n" +
                    "Introducing ECBA by IIBA:\n" +
                    "ECBA (Entry Certificate in Business Analysis) certification is the 1st and the entry-level of certification for business analysts governed by IIBA (International Institute for Business Analysis) Canada, the biggest Business Analysis Body in the world.\n\n" +
                    "This certification is most suited for junior business analysts or professionals who would like to become business analysts.\n" +
                    "ECBA certification from IIBA is recommended for beginners and the new entrants to get a solid footing in the world of business analysis.\n" +
                    "\n\n" +
                    "Introducing CDTP Cetification (Corporate Development Training Programme):\n" +
                    "This program is a unique combination of practices that are demanded by the employers when you face the competitive industry. The skills and qualities that are primarily noticed by any recruiter or interviewer is what we drive our focus on in this training programme. Certain attributes that can speak for you in any industry are as follows and these are a few of many skills that we incorporate in this unique curriculum.\n" +
                    "1. Interpersonal skills\n" +
                    "2. Analytical & Research Skills\n" +
                    "3. Flexibility / Adaptability\n" +
                    "4. Problem solving & decision making abilities\n" +
                    "5. Tactfulness\n" +
                    "6. Ethics &integrity\n" +
                    "7. Communication proficiency\n" +
                    "8. Professional Competence");
        }else if (get_course_type==2)
        {
            background_img.setImageDrawable(getResources().getDrawable(R.drawable.imigrg_ad));
            getSupportActionBar().setTitle("iGRAD in Finance");
            tv_des.setText("Oxford Brooks BSc (Hons) Degree in Applied Accounting\n" +
                    "Here, we introduce to you our International program where you will not only experience International level education but also gain an international degree by Oxford Brooks University, recognized worldwide.\n" +
                    "ACCA together with OBU (Oxford Brooks University) has developed a degree exclusively available to ACCA students. This program enables the student to acquire not only ACCA qualification but also a degree, which together proves to be more valuable. It provides students a better opportunity, as such advanced combinations are desired by employers in multiple fields.\n" +
                    "iGRAD in Finance, introduced by SISF incorporates ACCA qualification with extensive learning that includes ECBA by IIBA and CDTP (Corporate Development Training Program). Moreover, it also delivers the benefits of NCFM by NSE and regular guidance for your international graduation also known as OBU Mentorship, all under the same roof.\n" +
                    "NCFM by NSE\n" +
                    "Our NSE Academy Certification in Financial Markets, or NCFM, program is an online testing and certification program that examines the practical knowledge and skills required to operate in the financial markets. The NCFM program operates on our intranet and is administered through our designated test centers located across India.\n" +
                    "We provide regular guidance and support for all your NCFM by NSE certification modules. The certification is divided into 3 different modules, a beginner module, an intermediate module and an advance module.\n" +
                    "The programme was designed keeping in mind all aspects that can benefit our students in the real world. In today’s competitive epidemic the more knowledge you carry, the more you are valued. This programme of SISF justifies with this ideology and is keen to train the students to overcome all obstacles faced in this world of finance and accounting.");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
